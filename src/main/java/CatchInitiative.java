import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class CatchInitiative extends Behaviour {

//    public void

    @Override
    public void action() {
        ACLMessage receive = getAgent().receive();
        if (receive != null){
            String s = receive.getContent();
            String[] array = s.split(":");
            if (array[0].equals(getAgent().getLocalName())){
                System.out.println(getAgent().getLocalName() + " теперь я инициатор");
                Behaviour subBehaviour = new InitiateDistributedCalculation();
                getAgent().addBehaviour(subBehaviour);
                ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                String inf = array[1] + array[2];
                message.setContent(inf);
                message.addReceiver(new AID(getAgent().getLocalName(), false));
                getAgent().send(message);
            }
        }else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
