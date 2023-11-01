import jade.core.Agent;
import lombok.Data;

@Data
public class FunctionAgent extends Agent {
    boolean flg = true;
    @Override
    protected void setup() {
        System.out.println(getLocalName() + " Я Игрок");
        if (getLocalName().equals("Agent1") && flg) {
            addBehaviour(new InitiateDistributedCalculation());
            flg = false;
        }
            addBehaviour(new CalcMyFynction());
            addBehaviour(new CatchInitiative());

    }
}
