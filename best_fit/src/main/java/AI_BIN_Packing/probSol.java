package AI_BIN_Packing;


public class probSol{
    long duration;
    int numBins;
    int weigth[];
    int c;
    int n;
    int id;

    public probSol(int weight[], int c){
        this.weigth = weight;
        this.c = c;
        this.n = weight.length;
    }

    public probSol(int weight[], int c, int id){
        this(weight, c);
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public long getDuration(){
        return this.duration;
    }
    public int getNumBins(){
        return this.numBins;
    }
    public int[] getWeight(){
        return this.weigth;
    }
    public int getC(){
        return this.c;
    }
    public int getN(){
        return this.n;
    }
    public void setDuration(long duration){
        this.duration = duration;
    }
    public void setNumBins(int numBins){
        this.numBins = numBins;
    }
    public void setWeight(int weight[]){
        this.weigth = weight;
    }
    public void setC(int c){
        this.c = c;
    }
    public void setN(int n){
        this.n = n;
    }
}