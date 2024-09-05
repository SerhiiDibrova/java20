package com.onlyfullstack.optional;

import java.util.Optional;

public class OptionalFeature {

    public static void main(String[] args) {
        getGraphicsCardSize(createSmartPhoneWithGraphicsCard());
        getGraphicsCardSize(createSmartPhoneWithoutGraphicsCard());
        getGraphicsCardSize(createSmartPhoneWithEmptyGraphicsCard());
    }

    private static void getGraphicsCardSize(SmartPhone smartPhone) {
        String size = smartPhone.getGraphicsCard() 
                                .map(GraphicsCard::getGraphicsMemory) 
                                .map(GraphicsMemory::getDedicatedMemory)
                                .orElse("unknown");
        System.out.println("Size : " + size + ", for Object : "+smartPhone.toString());
    }
    
    private static void ofVsOfNullable() {
        Optional<GraphicsCard> graphicsCard = Optional.of(new GraphicsCard());
        if(graphicsCard.isPresent()) {
            System.out.println(graphicsCard.get());
        }
        
        graphicsCard.ifPresent(System.out::println);
        GraphicsCard newCard = graphicsCard.get();
    }

    private static SmartPhone createSmartPhoneWithGraphicsCard() {
        SmartPhone smartPhone = new SmartPhone();
        GraphicsMemory memory = new GraphicsMemory("4 GB", "");
        Optional<GraphicsCard> card = Optional.ofNullable(new GraphicsCard("4 gb", memory));
        smartPhone.setGraphicsCard(card);
        return smartPhone;
    }
    
    private static SmartPhone createSmartPhoneWithoutGraphicsCard() {
        SmartPhone smartPhone = new SmartPhone();
        Optional<GraphicsCard> card = Optional.ofNullable(null);
        smartPhone.setGraphicsCard(card);
        return smartPhone;
    }
    
    private static SmartPhone createSmartPhoneWithEmptyGraphicsCard() {
        SmartPhone smartPhone = new SmartPhone();
        Optional<GraphicsCard> card = Optional.ofNullable(new GraphicsCard());
        smartPhone.setGraphicsCard(card);
        return smartPhone;
    }

    private static class SmartPhone {
        private Optional<GraphicsCard> graphicsCard;

        public Optional<GraphicsCard> getGraphicsCard() {
            return graphicsCard;
        }

        public void setGraphicsCard(Optional<GraphicsCard> graphicsCard) {
            this.graphicsCard = graphicsCard;
        }

        @Override
        public String toString() {
            return "SmartPhone{" +
                    "graphicsCard=" + graphicsCard +
                    '}';
        }
    }

    private static class GraphicsCard {
        private String memory;
        private GraphicsMemory graphicsMemory;

        public GraphicsCard() {}

        public GraphicsCard(String memory, GraphicsMemory graphicsMemory) {
            this.memory = memory;
            this.graphicsMemory = graphicsMemory;
        }

        public GraphicsMemory getGraphicsMemory() {
            return graphicsMemory;
        }
    }

    private static class GraphicsMemory {
        private String dedicatedMemory;
        private String sharedMemory;

        public GraphicsMemory(String dedicatedMemory, String sharedMemory) {
            this.dedicatedMemory = dedicatedMemory;
            this.sharedMemory = sharedMemory;
        }

        public String getDedicatedMemory() {
            return dedicatedMemory;
        }
    }
}