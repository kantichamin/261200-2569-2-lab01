public class AirPurifier {
    // Instance variables
    String model;
    String serialNo;
    String mode;
    int pm2_5Level;
    double price;
    boolean power;

    // Class variables
    static int totalProduce;
    static int airCount;
    static int proCount;
    static int lifeCount;

    static double airPrice = 6000;
    static double proPrice = 10000;
    static double lifePrice = 4000;

    // Constructor
    AirPurifier(String model, String serialNo){
        if(!model.equals("Air") && !model.equals("Pro") && !model.equals("Life")){
            IO.println("We only have the model : [ Pro ]  [ Air ]  [ Life ]");
            return;
        }

        this.model = model;
        this.serialNo = serialNo;
        this.power = false;
        this.mode = "off";
        this.pm2_5Level = 0;

        if(model.equals("Air")){
            this.price = airPrice;
            airCount++;
        }else if(model.equals("Pro")){
            this.price = proPrice;
            proCount++;
        }else{
            this.price = lifePrice;
            lifeCount++;
        }
        totalProduce++;
    }

    // instance methods
    void turnOn(){
        power = true;
        mode = "auto";
        IO.println(model + " - " + serialNo + " is ON");
    }

    void turnOff(){
        power = false;
        mode = "OFF";
        IO.println(model + " - " + serialNo + " is OFF");
    }

    void setPm2_5Level(int level){
        if(level < 0) {
            IO.println("PM2.5 level must not be negative.");
            return;
        }
        pm2_5Level = level;
        IO.println(model + " - " + serialNo + " PM2.5 level : " + pm2_5Level);
    }

    void modeAirPurifier(){
        if(!power){
            IO.println(model + " - " + serialNo + " is OFF");
            return;
        }
        if(pm2_5Level >= 25){
            mode = "Always-On";
            IO.println(model + " - " + serialNo + " set to Always-On mode");
        }else{
            mode = "sleep";
            IO.println(model + " - "  + serialNo + " Set to sleep mode to save energy");
        }
    }

    //Class methods
    static String mostProducedModel(){
        if (totalProduce == 0){
            return "None";
        }

        if(airCount >= proCount && airCount >= lifeCount){
            return "Air";
        }else if(proCount >= airCount && proCount >= lifeCount){
            return "Pro";
        }else{
            return "Life";
        }
    }

    static String mostProfitableModel(){
        if(totalProduce == 0){
            return "None";
        }

        double airTotal = airCount * (airPrice * 25 / 100);
        double proTotal = proCount * (proPrice * 25 / 100);
        double lifeTotal = lifeCount * (lifePrice * 25 / 100);

        if (airTotal >= proTotal && airTotal >= lifeTotal){
            return "Air";
        }else if(proTotal >= airTotal && proTotal >= lifeTotal){
            return "Pro";
        }else{
            return "Life";
        }
    }
}

class Lab02 {
    void main(){
        AirPurifier p1 = new AirPurifier("Pro", "01");
        AirPurifier a1 = new AirPurifier("Air", "01");
        AirPurifier a2 = new AirPurifier("Air", "02");

        p1.modeAirPurifier();
        p1.turnOn();
        p1.setPm2_5Level(40);
        p1.modeAirPurifier();

        a1.turnOn();
        a1.setPm2_5Level(15);
        a1.modeAirPurifier();

        p1.turnOff();
        a1.turnOff();

        IO.println("Total produced: " + AirPurifier.totalProduce);
        IO.println("Most produced model: " + AirPurifier.mostProducedModel());
        IO.println("Most profitable model: " + AirPurifier.mostProfitableModel());
    }
}