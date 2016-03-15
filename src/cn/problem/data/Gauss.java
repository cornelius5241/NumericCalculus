package cn.problem.data;

import Jama.Matrix;

/**
 * Created by Cornelius on 14.03.2016.
 */
public class Gauss {
    public static boolean testSingularMatrix(double[][] matrix,double[][] matrix_bar,double epsilon){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW3]Test singularity matrix using Gaussian algorithm - start.");
        int n=matrix.length;
        int l=0;
        partialPivot(l,matrix);
        while((l<n-1)&&(Math.abs(matrix[l][l])>epsilon)){
            for (int i = l+1; i < n; i++) {
                double f= -(matrix_bar[i][l]/matrix_bar[l][l]);
                for (int j = l+1; j < 2*n; j++) {
                    matrix_bar[i][j]=matrix_bar[i][j]+f*matrix_bar[l][j];
                }
                matrix_bar[i][l]=0.0;
            }
            l++;
            partialPivot(l,matrix);
            System.out.println("[Log "+System.currentTimeMillis()+"][HW3]Matrix intermediate, step : "+l+"\n"+NumericCalculus.simpleToString(matrix_bar));
        }
        System.out.println("[Log "+System.currentTimeMillis()+"][HW3]Test singularity matrix using Gaussian algorithm .\n"+NumericCalculus.simpleToString(matrix_bar));

        return Math.abs(matrix[l][l]) <= epsilon;
    }

    private static void partialPivot(int l,double[][] matrix){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW3]Find partial pivot - start. L=" +l );
        int n=matrix.length;
        int line=-1;
        double max=0;
        for (int i = l; i <n ; i++) {
            if(Math.abs(matrix[i][l])>Math.abs(max))
            {
                max=matrix[i][l];
                line = i;
            }
        }
        System.out.println("[Log "+System.currentTimeMillis()+"][HW3]Found pivot at line "+ line);
        //System.out.println("Matrix before:"+NumericCalculus.simpleToString(matrix));
        double aux;
        for (int i = 0; i < n; i++) {
            aux=matrix[l][i];
            matrix[l][i]=matrix[line][i];
            matrix[line][i]=aux;
        }
        //System.out.println("Matrix after:"+NumericCalculus.simpleToString(matrix));
    }

    public static double[][] getBarredMatrix(double matrix[][]){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW3]AnIn matrix calculus - start.");
        int dimension=matrix.length;
        double identity[][]=QR.getIdentityMatrix(dimension);
        double result[][]=new double[dimension][dimension*2];

        for (int i = 0; i < dimension; i++)
            System.arraycopy(matrix[i], 0, result[i], 0, dimension);

        for (int i = 0; i <dimension ; i++)
            System.arraycopy(identity[i], 0, result[i], dimension, dimension * 2 - dimension);
        System.out.println("[Log "+System.currentTimeMillis()+"][HW3]Finished AnIn matrix calculus.\n"+NumericCalculus.simpleToString(result));
        return result;
    }

    public static double[][] findInverseMatrix(double[][] matrix){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW3]Find inverse matrix - start.");
        int n = matrix.length;
        double result[][]=new double[n][n];
        double R[][]=new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, R[i], 0, n);
        }
        for (int j = n; j < 2*n; j++) {
            double col[]=new double[n];
            for (int i = 0; i < n; i++) {
                col[i]=matrix[i][j];
            }
            double sol[]=calculateSolution(R,col);
            for (int i = 0; i < n; i++) {
                result[i][j-n]=sol[i];
            }
        }

        System.out.println("[Log "+System.currentTimeMillis()+"][HW3]Find inverse matrix .\n"+NumericCalculus.simpleToString(result));
        return result;
    }

    public static double[] calculateSolution(double matrixA[][], double vectorB[]){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]Liniar System calculus - start.");
        int dimension=vectorB.length;

        Matrix A= new Matrix(matrixA);
        Matrix b =new Matrix(vectorB,dimension);
        Matrix res=A.solve(b);
        double result[]=new double[dimension];
        for (int i = 0; i < dimension; i++) {
            result[i]=res.get(i,0);
        }

        return result;
    }

    public static double matrixNorm(double[][] matrix){
        double n=matrix.length;
        double max = -1;
        for (int j = 0; j < n; j++) {
            double sum=0;
            for (double[] aMatrix : matrix) {
                sum += Math.abs(aMatrix[j]);
            }
            if(sum>max)max=sum;
        }
        return max;
    }


    public static double[][] mulMatrix(double[][] A, double[][] B){
        int dimension=A.length;
        double[][] res = new double[dimension][dimension];
        for (int line = 0; line < dimension; line++) {
            for (int col = 0; col <dimension ; col++) {
                double element=0;
                for (int i = 0; i < dimension; i++) {
                    element+=A[line][i]*B[i][col];
                }
                res[line][col]=element;
            }
        }
        return res;
    }

    public static double calculateCorrectness(double[][] matrix, double[][] inverse){
        int n=matrix.length;
        double identity[][]=QR.getIdentityMatrix(n);
        double res[][]=mulMatrix(matrix,inverse);

        for (int i = 0; i < n; i++) {
          res[i][i]=res[i][i]-identity[i][i];
        }
        return matrixNorm(res);
    }

    public static double[][] generateSubArray (double matrix[][], int n, int col){
        double[][] subarr = new double[n-1][];
        for (int k=0; k<(n-1); k++)
            subarr[k] = new double[n-1];

        for (int i=1; i<n; i++)
        {
            int j2=0;
            for (int j=0; j<n; j++)
            {
                if(j == col)
                    continue;
                subarr[i-1][j2] = matrix[i][j];
                j2++;
            }
        }
        return subarr;
    }

    public static double calcualteDeterminant(double matrix[][], int n) {
        double res;

        //  1x1 matrix
        if (n == 1)
            res = matrix[0][0];
            //  2x2 matrix
        else if (n == 2)
            res = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
            // NxN matrix
        else {
            res = 0;
            for (int j = 0; j < n; j++) {
                double subarr[][] = generateSubArray(matrix, n, j);
                res += Math.pow(-1.0, 1.0 + j + 1.0) * matrix[0][j] * calcualteDeterminant(subarr, n - 1);
            }
        }
        return res;
    }
}
