import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class InitiateDistributedCalculation extends OneShotBehaviour {
//    boolean flg1 = true;
    //    String min;
//    double x = ThreadLocalRandom.current().nextInt(-10, 11);
//    double delta = 2;
    @Override
    public void action() {
        ACLMessage receive = getAgent().receive();
        String str;
        if (receive != null) {
            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
            str = receive.getContent();
            message.setContent(str);
            message.addReceiver(new AID("Agent1", false));
            message.addReceiver(new AID("Agent2", false));
            message.addReceiver(new AID("Agent3", false));
            getAgent().send(message);
//            flg1 = false;
        } else {
            str = "10:2";
            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
            message.setContent(str);
            message.addReceiver(new AID("Agent1", false));
            message.addReceiver(new AID("Agent2", false));
            message.addReceiver(new AID("Agent3", false));
            getAgent().send(message);


        }
        int i = 0;
        double y1 = 0;
        double y2 = 0;
        double y3 = 0;
        ACLMessage receive1 = getAgent().receive();
//        while (i < 3) {
            while (receive1 != null && i < 3) {
                System.out.println(receive1.getSender().getLocalName() + " " + receive1.getContent() + " РЕЗУЛЬТАТ");
                String s = receive1.getAllReceiver().toString();
                String[] array = s.split(":");
                y1 += Double.parseDouble(array[0]);
                y2 += Double.parseDouble(array[1]);
                y3 += Double.parseDouble(array[2]);
                i++;
//            } else {
//                block();
//            }
        }
            if (i == 3){
        System.out.println(y1 + " " + y2 + " " + y3);
        String str1;
        if (y1 < y2) {
            str1 = Double.toString(
                    Double.parseDouble(str.split(":")[0]) - Double.parseDouble(str.split(":")[1]))
                    + ":" + str.split(":")[1];

        } else if (y3 < y2) {
            str1 = Double.toString(
                    Double.parseDouble(str.split(":")[0]) + Double.parseDouble(str.split(":")[1]))
                    + ":" + str.split(":")[1];
        } else {
            str1 = str.split(":")[0]
                    + ":" + Double.toString(Double.parseDouble(str.split(":")[1]) / 2);
        }
        System.out.println(str1 + " ЗНАЧНИЕ ФУНКЦИИ");

        if (getAgent().getLocalName().equals("Agent1")) {
            ACLMessage message1 = new ACLMessage(ACLMessage.INFORM);
            message1.setContent("Agent2" + str1);
            message1.addReceiver(new AID("Agent2", false));
            getAgent().send(message1);
        } else if (getAgent().getLocalName().equals("Agent2")) {
            ACLMessage message1 = new ACLMessage(ACLMessage.INFORM);
            message1.setContent("Agent3" + str1);
            message1.addReceiver(new AID("Agent3", false));
            getAgent().send(message1);
        } else {
            ACLMessage message1 = new ACLMessage(ACLMessage.INFORM);
            message1.setContent("Agent1" + str1);
            message1.addReceiver(new AID("Agent1", false));
            getAgent().send(message1);
        }
    }}
}


//                    if (min == null) {
//                        min = array[1];
//                    }
//                    if (Double.parseDouble(array[0]) < Double.parseDouble(min)) {
//                        min = array[0];
//                    } else if (Double.parseDouble(array[2]) < Double.parseDouble(min)) {
//                        min = array[2];
//                    } else {
//                        min = array[1];
//                        delta = delta / 2;
//                    }

//            System.out.println(y1 + "жопа " + y2 + "жопа" + y3);
//            if (min != null){
//                x = Double.parseDouble(min);

//            System.out.println("Минимум  " + x + "; " + "дельта  " + delta);
//        }
//        flg = true;

