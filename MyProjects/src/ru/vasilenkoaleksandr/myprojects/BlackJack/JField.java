package ru.vasilenkoaleksandr.myprojects.BlackJack;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by vasilenko.aleksandr on 09.06.2016.
 */
public class JField extends JFrame {

    private ArrayList<Card> player;
    private ArrayList<Card> dealer;
    private JPanel dealerCards;
    private JPanel playerCards;
    private int scorePlayer;
    private int scoreDealer;
    private static final Dimension BUTTONS = new Dimension(150, 30);
    private static final Dimension PANELS = new Dimension(200, 140);

    public JField() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("BlackJack");
        setSize(400, 400);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(layout);

        JLabel dealerLabel = new JLabel("Диллер");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(dealerLabel, c);

        //карты диллера
        dealerCards = new JPanel();
        dealerCards.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        dealerCards.setPreferredSize(PANELS);
        dealer = new ArrayList<Card>();
        dealer.add(FabricaCard.getInstance().getRandomCard());
        printCards(dealer, dealerCards);

        addDealerCards();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(dealerCards, c);

        //кнопки игрока
        JButton moreButton = new JButton("Ещё!");
        moreButton.setPreferredSize(BUTTONS);
        moreButton.addActionListener(e -> {
            player.add(FabricaCard.getInstance().getRandomCard());
            printCards(player, playerCards);
        });
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        add(moreButton, c);

        JButton pasButton = new JButton("Хватит!");
        pasButton.setPreferredSize(BUTTONS);
        c.gridy = 2;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        add(pasButton, c);

        //Карты игрока
        playerCards = new JPanel();
        playerCards.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        playerCards.setPreferredSize(PANELS);
        player = new ArrayList<Card>();
        player.add(FabricaCard.getInstance().getRandomCard());
        player.add(FabricaCard.getInstance().getRandomCard());
        printCards(player, dealerCards);
        getSum(player);

        c.gridy = 3;
        c.gridx = 0;
        c.gridwidth = 2;
        c.weightx = 0;
        add(playerCards, c);


        JLabel playerLabel = new JLabel("Игрок");
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        add(playerLabel, c);

        setVisible(true);


    }

    private void getSum(ArrayList<Card> arrayList) {
        int sum = 0;
        for (Card card : arrayList) {
            sum += card.getRank();
        }
        if(sum == 21){
            JOptionPane.showInternalMessageDialog(getContentPane(),"Невероятное везение!\nBlackJack при раздаче!!","Победа",JOptionPane.INFORMATION_MESSAGE);
        }else if(sum>21){
            JOptionPane.showInternalMessageDialog(getContentPane(),"Проигрыш!\nпопробуй ещё раз","Проигрыш",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void printCards(ArrayList<Card> player, JPanel panelCards) {
        panelCards.removeAll();
        for (Card card : player) {
            panelCards.add((Component) card);
        }
        panelCards.updateUI();
    }


    private void addDealerCards() {
        //добавление карт диллеру

    }
}
