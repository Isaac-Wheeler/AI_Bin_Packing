package ai_bin_packing;


public class probSol{
    long duration;
    int numBins;
    int weigth[];
    int c;
    int n;
    int id;
    int next_fit;
    int first_fit;
    int best_fit;
    int nf_time;
    int ff_time;
    int bf_time;


    public probSol() {
    }

    public probSol(int weight[], int c){
        this.weigth = weight;
        this.c = c;
        this.n = weight.length;
    }

    public probSol(int weight[], int c, int id){
        this(weight, c);
        this.id = id;
    }


    public probSol(long duration, int numBins, int weigth[], int c, int id, int next_fit, int first_fit, int best_fit, int nf_time, int ff_time, int bf_time) {
        this(weigth, c, id);
        this.duration = duration;
        this.numBins = numBins;
        this.next_fit = next_fit;
        this.first_fit = first_fit;
        this.best_fit = best_fit;
        this.nf_time = nf_time;
        this.ff_time = ff_time;
        this.bf_time = bf_time;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getNumBins() {
        return this.numBins;
    }

    public void setNumBins(int numBins) {
        this.numBins = numBins;
    }

    public int[] getWeigth() {
        return this.weigth;
    }

    public void setWeigth (int weigth[]) {
        this.weigth = weigth;
    }

    public int getC() {
        return this.c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getN() {
        return this.n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNext_fit() {
        return this.next_fit;
    }

    public void setNext_fit(int next_fit) {
        this.next_fit = next_fit;
    }

    public int getFirst_fit() {
        return this.first_fit;
    }

    public void setFirst_fit(int first_fit) {
        this.first_fit = first_fit;
    }

    public int getBest_fit() {
        return this.best_fit;
    }

    public void setBest_fit(int best_fit) {
        this.best_fit = best_fit;
    }

    public int getNf_time() {
        return this.nf_time;
    }

    public void setNf_time(int nf_time) {
        this.nf_time = nf_time;
    }

    public int getFf_time() {
        return this.ff_time;
    }

    public void setFf_time(int ff_time) {
        this.ff_time = ff_time;
    }

    public int getBf_time() {
        return this.bf_time;
    }

    public void setBf_time(int bf_time) {
        this.bf_time = bf_time;
    }

    public probSol duration(long duration) {
        this.duration = duration;
        return this;
    }

    public probSol numBins(int numBins) {
        this.numBins = numBins;
        return this;
    }

    public probSol weigth (int weigth[]) {
        this.weigth = weigth;
        return this;
    }

    public probSol c(int c) {
        this.c = c;
        return this;
    }

    public probSol n(int n) {
        this.n = n;
        return this;
    }

    public probSol id(int id) {
        this.id = id;
        return this;
    }

    public probSol next_fit(int next_fit) {
        this.next_fit = next_fit;
        return this;
    }

    public probSol first_fit(int first_fit) {
        this.first_fit = first_fit;
        return this;
    }

    public probSol best_fit(int best_fit) {
        this.best_fit = best_fit;
        return this;
    }

    public probSol nf_time(int nf_time) {
        this.nf_time = nf_time;
        return this;
    }

    public probSol ff_time(int ff_time) {
        this.ff_time = ff_time;
        return this;
    }

    public probSol bf_time(int bf_time) {
        this.bf_time = bf_time;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof probSol)) {
            return false;
        }
        probSol probSol = (probSol) o;
        return duration == probSol.duration && numBins == probSol.numBins && weigth == probSol.weigth && c == probSol.c && n == probSol.n && id == probSol.id && next_fit == probSol.next_fit && first_fit == probSol.first_fit && best_fit == probSol.best_fit && nf_time == probSol.nf_time && ff_time == probSol.ff_time && bf_time == probSol.bf_time;
    }

    public String getWeigthToString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n-1; i++) {
            sb.append(weigth[i]);
            sb.append(",");
        }
        for (int i = n-1; i < 10 ; i++) {
            sb.append("0,");
        }
        return sb.toString();

    }

    @Override
    public String toString() {
        return getC()       + "," +
               getWeigthToString() 
                + getNumBins() + "\n";
    }


}