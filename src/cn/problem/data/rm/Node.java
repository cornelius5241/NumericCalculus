package cn.problem.data.rm;

/**
 * Created by Cornelius on 22.03.2016.
 */
public class Node {

        double value;
        int i;

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public Node(double value, int i){
            this.value=value;
            this.i=i;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", i=" + i +
                    '}';
        }
    }

