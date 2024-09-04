package com.onlyfullstack.optional;

import java.util.Optional;

import com.onlyfullstack.bean.GraphicsCard;
import com.onlyfullstack.bean.GraphicsMemory;
import com.onlyfullstack.bean.SmartPhone;

public class OptionalFeature {

    public static void main(String[] args) {
        getGraphicsCardSize(createSmartPhoneWithGraphicsCard());
        getGraphicsCardSize(createSmartPhoneWithoutGraphicsCard());
        getGraphicsCardSize(createSmartPhoneWithEmptyGraphicsCard());
    }

    private static void getGraphicsCardSize(SmartPhone smartPhone) {
        String size = switch (smartPhone.getGraphicsCard()) {
            case empty -> "unknown";
            case present(GraphicsCard graphicsCard) -> 
                switch (graphicsCard.getGraphicsMemory()) {
                    case null -> "unknown";
                    default -> graphicsCard.getGraphicsMemory().getDedicatedMemory();
                };
        };
        System.out.println("Size : " + size + ", for Object : " + smartPhone.toString());
    }

    private static void ofVsOfNullable() {
        Optional<GraphicsCard> graphicsCard = Optional.of(new GraphicsCard());
        if (graphicsCard.isPresent()) {
            System.out.println(graphicsCard.get());
        }
        
        graphicsCard.ifPresent(System.out::println);
        GraphicsCard newCard = graphicsCard.get();
    }

    private static SmartPhone createSmartPhoneWithGraphicsCard() {
        SmartPhone smartPhone = new SmartPhone();
        GraphicsMemory memory = new GraphicsMemory("4 GB", "");
        Optional<GraphicsCard> card = Optional.of(new GraphicsCard("4 gb", memory));
        smartPhone.setGraphicsCard(card);
        return smartPhone;
    }

    private static SmartPhone createSmartPhoneWithoutGraphicsCard() {
        SmartPhone smartPhone = new SmartPhone();
        Optional<GraphicsCard> card = Optional.empty();
        smartPhone.setGraphicsCard(card);
        return smartPhone;
    }

    private static SmartPhone createSmartPhoneWithEmptyGraphicsCard() {
        SmartPhone smartPhone = new SmartPhone();
        GraphicsCard graphicsCard = new GraphicsCard();
        Optional<GraphicsCard> card = Optional.of(graphicsCard);
        smartPhone.setGraphicsCard(card);
        return smartPhone;
    }
}