import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CatchInitiative extends Behaviour {
    private boolean flg = false;

//    public void

    @Override
    public void action() {
        ACLMessage receive = getAgent().receive(MessageTemplate.MatchConversationId("inf"));
        if (receive != null && receive.getContent().split(":")[0].equals(getAgent().getLocalName())){
            String s = receive.getContent();
            String[] array = s.split(":");
//            if (array[0].equals(getAgent().getLocalName())){
            System.out.println(getAgent().getLocalName() + " теперь я инициатор");
            getAgent().addBehaviour(new InitiateDistributedCalculation());
            ACLMessage message = new ACLMessage(ACLMessage.INFORM);


            String inf = array[1] + ":" + array[2];
//                System.out.println(inf);
            message.setContent(inf);
            message.addReceiver(new AID(getAgent().getLocalName(), false));
            getAgent().send(message);
//            Behaviour subBehaviour = new InitiateDistributedCalculation();


//        else if (!flg){
//            getAgent().addBehaviour(new InitiateDistributedCalculation());
//            flg = true;
        }
        else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
