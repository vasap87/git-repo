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

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(dealerCards, c);

        //кнопки игрока
        JButton moreButton = new JButton("Ещё!");
        moreButton.setPreferredSize(BUTTONS);
        moreButton.addActionListener(e -> addPlayerCards());
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        add(moreButton, c);

        JButton pasButton = new JButton("Хватит!");
        pasButton.setPreferredSize(BUTTONS);
        pasButton.addActionListener(e -> addDealerCards());
        c.gridy = 2;
        c.gridx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        add(pasButton, c);

        //Карты игрока
        playerCards = new JPanel();
        playerCards.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        playerCards.setPreferredSize(PANELS);

        setup();

        if (getSum(player) == 21) {
            JOptionPane.showInternalConfirmDialog(getContentPane(), "Black Jack", "Победа Игрока!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            scorePlayer++;
            setup();
        }

        c.gridy = 3;
        c.gridx = 0;
        c.gridwidth = 2;
        c.weightx = 0;
        add(playerCards, c);


        JLabel playerLabel = new JLabel("Сумма игрока");
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        add(playerLabel, c);

        setVisible(true);


    }

    private void addPlayerCards() {
        player.add(FabricaCard.getInstance().getRandomCard());
        printCards(player, playerCards);
        if (getSum(player) == 21) {
            JOptionPane.showInternalConfirmDialog(getContentPane(), "Black Jack", "Победа Игрока!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            scorePlayer++;
            setup();
        } else if (getSum(player) > 21) {
            JOptionPane.showInternalConfirmDialog(getContentPane(), "Не повезло", "Проигрыш!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            scoreDealer++;
            setup();
        }
    }

    private void setup() {
        dealer = new ArrayList<Card>();
        dealer.add(FabricaCard.getInstance().getRandomCard());
        printCards(dealer, dealerCards);

        player = new ArrayList<Card>();
        player.add(FabricaCard.getInstance().getRandomCard());
        player.add(FabricaCard.getInstance().getRandomCard());
        printCards(player, dealerCards);
    }

    private int getSum(ArrayList<Card> arrayList) {
        int sum = 0;
        for (Card card : arrayList) {
            if (sum > 10 && card.getRank() == 11) {
                sum += card.getRank() - 10;
            } else {
                sum += card.getRank();
            }
        }
        return sum;

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
        while (true) {
            dealer.add(FabricaCard.getInstance().getRandomCard());
            printCards(dealer, dealerCards);
            if (getSum(dealer) == 21) {
                JOptionPane.showInternalConfirmDialog(getContentPane(), "Black Jack", "Победа диллера!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                scoreDealer++;
                setup();
            } else if (getSum(dealer) > 21) {
                JOptionPane.showInternalConfirmDialog(getContentPane(), "Не повезло", "Проигрыш!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                scorePlayer++;
                setup();
            }
        }
    }
}
