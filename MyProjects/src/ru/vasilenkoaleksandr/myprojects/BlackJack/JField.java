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
    private static final Dimension BUTTONS = new Dimension(150,30);
    private static final Dimension PANELS = new Dimension(200,100);

    public JField(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("BlackJack");
        setSize(400,400);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(layout);

        JLabel dealerLabel = new JLabel("Диллер");
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        add(dealerLabel,c);

        //карты диллера
        dealerCards = new JPanel();
        dealerCards.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        dealerCards.setPreferredSize(PANELS);
        dealer = new ArrayList<Card>();
        dealer.add(FabricaCard.getInstance().getRandomCard());
        dealer.add(FabricaCard.getInstance().getRandomCard());
        for (Card card: dealer ) {
            dealerCards.add((Component) card);
        }

        addDealerCards();
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=2;
        add(dealerCards,c);

        //кнопки игрока
        JButton moreButton = new JButton("Ещё!");
        moreButton.setPreferredSize(BUTTONS);
        moreButton.addActionListener(e -> {
            player.add(FabricaCard.getInstance().getRandomCard());
            printCards(player);
        });
        c.gridy=2;
        c.gridx=0;
        c.gridwidth=1;
        c.fill = GridBagConstraints.BOTH;
        add(moreButton,c);

        JButton pasButton = new JButton("Хватит!");
        pasButton.setPreferredSize(BUTTONS);
        c.gridy=2;
        c.gridx=1;
        c.gridwidth=1;
        c.fill = GridBagConstraints.BOTH;
        add(pasButton,c);

        //Карты игрока
        playerCards = new JPanel();
        playerCards.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        playerCards.setPreferredSize(PANELS);
        player = new ArrayList<Card>();
        player.add(FabricaCard.getInstance().getRandomCard());
        player.add(FabricaCard.getInstance().getRandomCard());
        printCards(player);

        c.gridy=3;
        c.gridx=0;
        c.gridwidth=2;
        c.weightx=0;
        add(playerCards,c);


        JLabel plalerLabel = new JLabel("Игрок");
        c.gridx=0;
        c.gridy=4;
        c.gridwidth=2;
        c.fill = GridBagConstraints.NONE;
        add(plalerLabel,c);

        setVisible(true);




    }

    private void printCards(ArrayList<Card> player) {
        playerCards.removeAll();
        for (Card card: player ) {
            playerCards.add((Component) card);
        }
        playerCards.updateUI();
    }


    private void addDealerCards() {
        //добавление карт диллеру


    }
}
