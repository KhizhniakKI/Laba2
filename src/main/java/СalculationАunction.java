import jade.core.Agent;

class СalculationАunction {
    public static double Сalculatio1(String a, String q, String qq){
        double y = 0;
        double x = Double.parseDouble(q);
        double delta = Double.parseDouble(qq);
        if (a.equals("Agent1")){
            y = Math.exp(-0.5*(x - delta));
        }
        else if (a.equals("Agent2")) {
            y = 0.5 * (x - delta) + 7;
        }
        else if (a.equals("Agent3")) {
            y = Math.cos(x - delta);
        }
        return y;
    }
    public static double Сalculatio2(String a, String q){
        double x = Double.parseDouble(q);
        double y = 0;
        if (a.equals("Agent1")){
            y = Math.exp(-0.5*x);
        }
        else if (a.equals("Agent2")) {
            y = 0.5 * x + 7;
        }
        else if (a.equals("Agent3")) {
            y = Math.cos(x);
        }

        return y;
    }
    public static double Сalculatio3(String a, String q, String qq){
        double x = Double.parseDouble(q);
        double delta = Double.parseDouble(qq);
        double y = 0;
        if (a.equals("Agent1")){
            y = Math.exp(-0.5*(x + delta));
        }
        else if (a.equals("Agent2")) {
            y = 0.5 * (x + delta) + 7;
        }
        else if (a.equals("Agent3")) {
            y = Math.cos(x + delta);
        }

        return y;
    }
}
