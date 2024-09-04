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
        traditionalApproach(createSmartPhoneWithGraphicsCard());
    }

    private static void getGraphicsCardSize(SmartPhone smartPhone) {
        String size = smartPhone.getGraphicsCard()
                .map(GraphicsCard::getGraphicsMemory)
                .flatMap(graphicsMemory -> Optional.ofNullable(graphicsMemory.getDedicatedMemory()))
                .orElse("unknown");
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
        Optional<GraphicsCard> card = Optional.of(new GraphicsCard());
        smartPhone.setGraphicsCard(card);
        return smartPhone;
    }
    
    private static void traditionalApproach(SmartPhone smartPhone) {
        String size = "unknown";
        if (smartPhone.getGraphicsCard().isPresent()) {
            GraphicsCard graphicsCard = smartPhone.getGraphicsCard().get();
            if (graphicsCard != null) {
                GraphicsMemory graphicsMemory = graphicsCard.getGraphicsMemory();
                if (graphicsMemory != null && graphicsMemory.getDedicatedMemory() != null) {
                    size = graphicsMemory.getDedicatedMemory();
                }
            }
        }
        System.out.println("Size : " + size + ", for Object : " + smartPhone.toString());
    }
}