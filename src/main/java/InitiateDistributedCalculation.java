import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class InitiateDistributedCalculation extends OneShotBehaviour {
    double y1 = 0;
    double y2 = 0;
    double y3 = 0;
    String str;
    int i = 0;

    public void onStart(){
        ACLMessage receive = getAgent().blockingReceive(300);
        if (receive != null) {
            i = 0;

            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
            message.setConversationId("x_delta");
            str = receive.getContent();
            message.setContent(str);
            if (getAgent().getLocalName().equals("Agent1")){
//                ACLMessage message = new ACLMessage(ACLMessage.INFORM);
//                message.setContent(str);
                message.addReceiver(new AID("Agent2", false));
                message.addReceiver(new AID("Agent3", false));
                getAgent().send(message);
                y1 = СalculationАunction.Сalculatio1("Agent1", str.split(":")[0], str.split(":")[1]);
                y2 = СalculationАunction.Сalculatio2("Agent1", str.split(":")[0]);
                y3 = СalculationАunction.Сalculatio3("Agent1", str.split(":")[0], str.split(":")[1]);
            }
            else if (getAgent().getLocalName().equals("Agent2")){
                System.out.println(getAgent().getLocalName() + " НИУ МЭИ " + receive.getContent());
                message.addReceiver(new AID("Agent1", false));
                message.addReceiver(new AID("Agent3", false));
                y1 = СalculationАunction.Сalculatio1("Agent2", str.split(":")[0], str.split(":")[1]);
                y2 = СalculationАunction.Сalculatio2("Agent2", str.split(":")[0]);
                y3 = СalculationАunction.Сalculatio3("Agent2", str.split(":")[0], str.split(":")[1]);
                getAgent().send(message);
            }
            else {
//                ACLMessage message = new ACLMessage(ACLMessage.INFORM);
//                message.setContent(str);
                message.addReceiver(new AID("Agent1", false));
                message.addReceiver(new AID("Agent2", false));
                getAgent().send(message);
                y1 = СalculationАunction.Сalculatio1("Agent3", str.split(":")[0], str.split(":")[1]);
                y2 = СalculationАunction.Сalculatio2("Agent3", str.split(":")[0]);
                y3 = СalculationАunction.Сalculatio3("Agent3", str.split(":")[0], str.split(":")[1]);
            }
//            message.addReceiver(new AID("Agent1", false));
//            message.addReceiver(new AID("Agent2", false));
//            message.addReceiver(new AID("Agent3", false));
//            getAgent().send(message);


//            flg1 = false;
        } else {
            str = "1:0.5";
            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
            message.setConversationId("x_delta");
            message.setContent(str);
//            message.addReceiver(new AID("Agent1", false));
            message.addReceiver(new AID("Agent2", false));
            message.addReceiver(new AID("Agent3", false));
            getAgent().send(message);
            y1 = СalculationАunction.Сalculatio1("Agent1", "1", "0.5");
            y2 = СalculationАunction.Сalculatio2("Agent1", "1");
            y3 = СalculationАunction.Сalculatio3("Agent1", "-1", "0.5");
        }
    }
    @Override
    public void action() {




//        ACLMessage receive = getAgent().receive();
//        ACLMessage receive1 ;
        while (i < 2) {
            ACLMessage receive1 = getAgent().receive(MessageTemplate.MatchConversationId("y1_y2_y3"));
            if (receive1 != null ) {
                System.out.println(receive1.getSender().getLocalName() + " " + receive1.getContent() + " РЕЗУЛЬТАТ");
                String s = receive1.getContent();
                String[] array = s.split(":");
                y1 += Double.parseDouble(array[0]);
                y2 += Double.parseDouble(array[1]);
                y3 += Double.parseDouble(array[2]);
                i++;
//            } else {
//                block();
//            }
            }
        }
        if (i == 2){
        System.out.println(y1 + "___" + y2 + " ___" + y3);
        String str1;
        if (y1 < y2 && y1 < y3) {
            str1 = Double.toString(
                    Double.parseDouble(str.split(":")[0]) - Double.parseDouble(str.split(":")[1]))
                    + ":" + str.split(":")[1];

        } else if (y3 < y2 && y3 < y1) {
            str1 = Double.toString(
                    Double.parseDouble(str.split(":")[0]) + Double.parseDouble(str.split(":")[1]))
                    + ":" + str.split(":")[1];
        } else {
            str1 = str.split(":")[0]
                    + ":" + Double.toString(Double.parseDouble(str.split(":")[1]) / 2);
        }
        System.out.println(str1 + " ЗНАЧНИЕ ФУНКЦИИ");
        if (Double.parseDouble((str1.split(":"))[1]) > 0.000001){

            if (getAgent().getLocalName().equals("Agent1")) {
                ACLMessage message1 = new ACLMessage(ACLMessage.INFORM);
                message1.setConversationId("inf");
                message1.setContent("Agent2" + ":" + str1);
                message1.addReceiver(new AID("Agent2", false));
                getAgent().send(message1);
            } else if (getAgent().getLocalName().equals("Agent2")) {
                ACLMessage message1 = new ACLMessage(ACLMessage.INFORM);
                message1.setConversationId("inf");
                message1.setContent("Agent3" + ":" + str1);
                message1.addReceiver(new AID("Agent3", false));
                getAgent().send(message1);
            } else {
                ACLMessage message1 = new ACLMessage(ACLMessage.INFORM);
                message1.setConversationId("inf");
                message1.setContent("Agent1" + ":" + str1);
                message1.addReceiver(new AID("Agent1", false));
                getAgent().send(message1);
            }
        }
        else {
            System.out.println("ПОБЕДАААААААААААААААААААААААААААААААААА");
        }
    }
    }
}



