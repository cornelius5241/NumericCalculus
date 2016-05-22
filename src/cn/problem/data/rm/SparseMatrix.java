package cn.problem.data.rm;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Cornelius on 22.03.2016.
 */
public class SparseMatrix {

    private  ArrayList<Node>[] matrix;
    private  ArrayList<Double> vector;
    private  int size;

    public ArrayList<Node>[] getMatrix() {
        return matrix;
    }

    public ArrayList<Double> getVector() {
        return vector;
    }

    public int getSize() {
        return size;
    }

    public SparseMatrix(){

    }

    public SparseMatrix(int n,int m){
        this.size=n;
        this.vector=new ArrayList<Double> (n);
        this.matrix= (ArrayList<Node>[])new ArrayList[size];
        for (int i = 0; i <size ; i++) {
            matrix[i]=new ArrayList<> (m);
        }
    }

    public  void loadFromFile(File file) {
        System.out.println("[Log "+System.currentTimeMillis()+"][HW4]Rare matrix - load from file.\n"+file.toString ()+"\n");

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream (file);
        } catch (FileNotFoundException e) {
            e.printStackTrace ( );
        }
        assert fstream != null;
        BufferedReader br = new BufferedReader(new InputStreamReader (fstream));
        String strLine;
        try {

            strLine=br.readLine();
            size=Integer.parseInt(strLine);
            strLine=br.readLine();
            vector=new ArrayList<>(size);

            for (int i = 0; i < size; i++) {
                strLine=br.readLine();
                vector.add (Double.parseDouble (strLine));
            }

            strLine=br.readLine();
            matrix= (ArrayList<Node>[])new ArrayList[size];
            for (int i = 0; i <size ; i++) {
                matrix[i]=new ArrayList<> (10);
            }

            while ((strLine = br.readLine()) != null &&!(strLine==null))   {
                //System.out.println("[Log "+System.currentTimeMillis()+"][HW4]Rare matrix - load from file.\n"+strLine+"\n");
                String elements[]=strLine.split (", ");
                matrix[Integer.parseInt(elements[1])].add (new Node (Double.parseDouble(elements[0]),Integer.parseInt(elements[2])));
            }
        } catch (IOException e) {

           e.printStackTrace ( );
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

    public  void loadFromFileOnlyMatrix(File file) {
        System.out.println("[Log "+System.currentTimeMillis()+"][HW4]Rare matrix - load from file.\n"+file.toString ()+"\n");

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream (file);
        } catch (FileNotFoundException e) {
            e.printStackTrace ( );
        }
        assert fstream != null;
        BufferedReader br = new BufferedReader(new InputStreamReader (fstream));
        String strLine;
        try {

            strLine=br.readLine();
            size=Integer.parseInt(strLine);
            strLine=br.readLine();
            //vector=new ArrayList<>(size);

            //for (int i = 0; i < size; i++) {
            //    strLine=br.readLine();
            //    vector.add (Double.parseDouble (strLine));
            //}

            strLine=br.readLine();
            matrix= (ArrayList<Node>[])new ArrayList[size];
            for (int i = 0; i <size ; i++) {
                matrix[i]=new ArrayList<> (10);
            }

            while ((strLine = br.readLine()) != null &&!(strLine==null))   {
                //System.out.println("[Log "+System.currentTimeMillis()+"][HW4]Rare matrix - load from file.\n"+strLine+"\n");
                String elements[]=strLine.split (", ");
                matrix[Integer.parseInt(elements[1])].add (new Node (Double.parseDouble(elements[0]),Integer.parseInt(elements[2])));
            }
        } catch (IOException e) {

            e.printStackTrace ( );
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

    public String testNrOfElements(int nr){
        String result="";
        for (int i = 0; i <size ; i++) {
            if(matrix[i].size ()>nr)
                result+=("line "+i+" has > "+nr+" elements\n");
        }
        if(result.equals (""))
            return "ok\n";
        return result;
    }

    public String simpleToString(){
        String result=Integer.toString(size)+"\n";
        for (int i = 0; i <size ; i++) {
            result+="\n"+Integer.toString (matrix[i].size ())+"\n";
            for (int j = 0; j < matrix[i].size () ; j++) {
                result+=(matrix[i].get(j)).toString ();
            }
        }
        return result;
    }

    public static SparseMatrix sum(SparseMatrix A, SparseMatrix B){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW4]Rare matrix - Sum.\n");
        SparseMatrix result=new SparseMatrix ();
        result.size=A.size;
        result.matrix= (ArrayList<Node>[])new ArrayList[result.size];

        for (int i = 0; i <result.size ; i++) {
            result.matrix[i]=new ArrayList<> ( A.matrix[i].size ()+B.matrix[i].size ());
            for (int j = 0; j < A.matrix[i].size() + B.matrix[i].size() ; j++) {

                if(A.matrix[i].size()<=j && B.matrix[i].size()<=j)
                    continue;

                if(A.matrix[i].size()<=j && B.matrix[i].size()>j){
                    Node node=new Node(B.matrix[i].get(j).getValue(),B.matrix[i].get(j).getI());
                    result.matrix[i].add (node);
                    continue;
                }
                //System.out.println("[Log "+System.currentTimeMillis()+"][HW4]Rare matrix - Sum.\n"+"i = "+i+" j = "+j+" a="+A.matrix[i].size ()+" b="+B.matrix[i].size ()+"\n");
                if(A.matrix[i].size()>j && B.matrix[i].size()<=j){
                    Node node=new Node(A.matrix[i].get(j).getValue(),A.matrix[i].get(j).getI());
                    result.matrix[i].add (node);
                    continue;
                }

                if (A.matrix[i].get(j).getI() == B.matrix[i].get(j).getI()) {
                    Node node = new Node (A.matrix[i].get (j).getValue() + B.matrix[i].get(j).getValue(), A.matrix[i].get(j).getI());
                    result.matrix[i].add (node);
                    continue;
                } else {
                    if (A.matrix[i].get (j).getI ( ) < B.matrix[i].get (j).getI ( )) {
                        Node node = new Node (A.matrix[i].get (j).getValue ( ), A.matrix[i].get (j).getI ( ));
                        result.matrix[i].add (node);
                        Node node2 = new Node (B.matrix[i].get (j).getValue ( ), B.matrix[i].get (j).getI ( ));
                        result.matrix[i].add (node2);
                        continue;
                    } else {
                        Node node = new Node (B.matrix[i].get (j).getValue ( ), B.matrix[i].get (j).getI ( ));
                        result.matrix[i].add (node);
                        Node node2 = new Node (A.matrix[i].get (j).getValue ( ), A.matrix[i].get (j).getI ( ));
                        result.matrix[i].add (node2);
                        continue;
                    }

                }
            }
        }

         result.vector=new ArrayList<>(result.size);
         for (int i = 0; i < result.size ; i++) {
            result.vector.add (A.vector.get(i)+B.vector.get(i));
         }

        return result;
    }

    public double getNodeValue(int line,int column){

        for (Node node:matrix[line] ) {
            if(node.getI()==column)
                return node.getValue();
        }
        return 0;
    }

    public static SparseMatrix multiplication(SparseMatrix A,SparseMatrix B){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW4]Rare matrix - Multiplication.\n");

        SparseMatrix result=new SparseMatrix ();
        result.size=A.size;
        result.matrix= (ArrayList<Node>[])new ArrayList[result.size];

        for (int line = 0; line < result.size ; line++) {
            result.matrix[line]=new ArrayList<>();
            for (int column = 0; column < result.size; column++) {
                //System.out.println("[Log "+System.currentTimeMillis()+"][HW4]Rare matrix - Multiplication.\n"+line+" and "+column);
                double sum=0;

                for (int i = 0; i < result.size; i++) {
                    sum+=A.getNodeValue(line,i)*B.getNodeValue(i,column);
                }
                result.matrix[line].add(new Node (sum,column));
            }
        }

        return result;
    }

    public static ArrayList<Double> mulMatrixVector(SparseMatrix A){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW4]Rare matrix - Multiplication with vector.\n");

        int size=A.size;
        ArrayList<Double> result=new ArrayList<>(size);

        ArrayList<Double> x=new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            x.add((double)i);
        }

        for (int line = 0; line < size; line++)
        {
            double S = 0;
            for (Node n:A.matrix[line]) {
                S+=x.get(n.getI())*n.getValue ();
            }
            result.add(S);
        }

        return result;
    }

     public static boolean testMatrix(SparseMatrix A,SparseMatrix B){
         if(A.size!=B.size)return false;
         for (int i = 0; i < A.size; i++) {
            if(A.matrix[i].equals (B.matrix[i]))return false;
         }
        return true;
     }

    public static boolean testVector(ArrayList<Double> A,ArrayList<Double> B){
        if(A.size ()!=B.size())return false;
        for (int i = 0; i < A.size(); i++) {
            if(A.get(i).equals (B.get(i)))return false;
        }
        return true;
    }

    public void generateRandomMatrix(int n,int procent_non_null){

        this.size=n;
        int MAX = n;
        int MIN = -n;

        Random r = new Random();
        Random rn = new Random();

        int elements=((int)(size * procent_non_null/100));
        matrix= (ArrayList<Node>[])new ArrayList[size];
        for (int i = 0; i <this.size ; i++) {
            matrix[i] = new ArrayList<> (elements*2);
        }

        for (int i = 0; i <this.size ; i++) {
            for (int j = 0; j <elements ; j++) {
                double randomValue = MIN + (MAX - MIN) * r.nextDouble();
                int random_i = (rn.nextInt() % this.size);
                if(random_i<0)random_i=-random_i;
                Node node = new Node (randomValue,random_i);
                matrix[i].add(node);

                Node node_sim=new Node (randomValue,i);
                matrix[random_i].add (node_sim);
            }
        }

    }

}
