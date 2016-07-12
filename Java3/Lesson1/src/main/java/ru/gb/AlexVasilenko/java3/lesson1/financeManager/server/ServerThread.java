package ru.gb.AlexVasilenko.java3.lesson1.financeManager.server;

import com.google.gson.Gson;
import ru.gb.AlexVasilenko.java3.lesson1.financeManager.server.Tools.SQLTools;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 11.07.2016.
 */
public class ServerThread implements Runnable {

    private Socket socket;
    private DataInputStream objIN;
    private DataOutputStream objOUT;
    private String login;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            objIN = new DataInputStream(socket.getInputStream());
            objOUT = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String fromUser = objIN.readUTF();
                String parts[] = fromUser.split("\t");
                switch (parts[0]) {
                    case "authorisation": {
                        if (SQLTools.getInstance().authorisationByLoginAndPassword(parts[1], parts[2])) {
                            login = parts[1];
                            sendToUser("good");
                        } else {
                            sendToUser("bad");
                        }
                        break;
                    }
                    case "registration": {
                        if (SQLTools.getInstance().registerNewUser(parts[1], parts[2])) {
                            login = parts[1];
                            sendToUser("good");
                        } else {
                            sendToUser("bad");
                        }
                        break;
                    }
                    case "getAccounts": {
                        List accountList = SQLTools.getInstance().getAccountsByUserName(login);
                        if(accountList.size()>0){
                            //делаем JSON строку для списка
                            Gson gson = new Gson();
                            String accounts = gson.toJson(accountList);
                            sendToUser("accounts\t"+accounts);
                        }else{
                            sendToUser("accounts empty");
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendToUser(String s) {
        try {
            objOUT.writeUTF(s);
            objOUT.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
