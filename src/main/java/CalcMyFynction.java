import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jdk.jfr.DataAmount;



public class CalcMyFynction extends Behaviour {
    @Override
    public void action() {
        ACLMessage receive = getAgent().receive();
        if (receive != null) {
            System.out.println(getAgent().getLocalName() + " Я получил х и дельту " +receive.getContent());
            String s = receive.getContent();
            String[] array = s.split(":");

            String y1 = Double.toString(СalculationАunction.Сalculatio1(getAgent().getLocalName(), array[0], array[1]));
            String y2 = Double.toString(СalculationАunction.Сalculatio2(getAgent().getLocalName(), array[0]));
            String y3 = Double.toString(СalculationАunction.Сalculatio3(getAgent().getLocalName(), array[0], array[1]));
            String result = y1 + ":" + y2 + ";" + y3;
            System.out.println(getAgent().getLocalName() + " Мой результат " + result);
            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
            message.setContent(result);
            message.addReceiver(new AID(receive.getSender().getLocalName(), false));
            getAgent().send(message);


        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
