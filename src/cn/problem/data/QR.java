package cn.problem.data;

import Jama.Matrix;

/**
 * Created by Cornelius on 14.03.2016.
 */
public class QR {
    public static double[][] getIdentityMatrix(int dimension){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]Identity matrix - start.");
        double identityMatrix[][]=new double[dimension][dimension];
        for (int i = 0; i < identityMatrix.length ; i++) {
            identityMatrix[i][i]=1.0;
        }
        return identityMatrix;
    }

    public static double[][] calculateQR(double matrixA[][],double vectorB[],double epsilon){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]QR calculus - start.");

        int dimension=matrixA.length;
        double Q_matrix[][]=getIdentityMatrix(dimension);
        double u_vector[]=new double[dimension];

        //constructia matricii P_r - constanta beta si vectorul u
        for (int r = 0; r < dimension ; r++) {
            double sigma=0.0;

            for (int i = r; i < dimension; i++) {
                sigma+=Math.pow(matrixA[i][r],2);
            }

            if(sigma<=epsilon)
                break;

            double k=Math.abs(sigma);

            if(matrixA[r][r]>0)
                k=-k;

            double beta=sigma-k*matrixA[r][r];

            u_vector[r]=matrixA[r][r]-k;

            for (int i = r+1; i < dimension; i++) {
                u_vector[i]=matrixA[i][r];
            }
            //transformarea coloanelor j = r+1,...,n

            for (int j = r+1; j <dimension ; j++) {

                double gamma=0.0;

                for (int i = r; i <dimension ; i++) {
                    gamma+=u_vector[i]*matrixA[i][j];
                }

                if ( Math.abs(gamma) > epsilon )
                    gamma=gamma/beta;
                else gamma=0;

                for (int i = r; i < dimension; i++) {
                    double current_a=matrixA[i][j];
                    matrixA[i][j]=current_a-gamma*u_vector[i];
                }
            }

            //transformarea coloanei r a matricii A
            matrixA[r][r]=k;
            for (int i = r+1; i <dimension ; i++) {
                matrixA[i][r]=0;
            }
            double gamma=0.0;
            for (int i = r; i < dimension; i++) {
                gamma+=u_vector[i]*vectorB[i];
            }

            if ( Math.abs(beta) > epsilon )
                gamma=gamma/beta;
            else gamma=0;

            for (int i = r; i < dimension; i++) {
                double current_b=vectorB[i];
                vectorB[i]=current_b-gamma*u_vector[i];
            }

            //Q = P*Q
            for (int j = 0; j <dimension ; j++) {
                gamma=0;
                for (int i = r; i < dimension; i++) {
                    gamma+=u_vector[i]*Q_matrix[i][j];
                }
                if ( Math.abs(beta) > epsilon )
                    gamma=gamma/beta;
                else gamma=0;

                for (int i = r; i < dimension; i++) {
                    double current_q=Q_matrix[i][j];
                    Q_matrix[i][j] = current_q-gamma*u_vector[i];
                }
            }
        }

        //System.out.println("[Log "+System.currentTimeMillis()+"][HW2]R matrix .\n"+simpleToString(matrixA));
        //System.out.println("[Log "+System.currentTimeMillis()+"][HW2]Q transposed .\n"+simpleToString(Q_matrix));
        //System.out.println("[Log "+System.currentTimeMillis()+"][HW2]B vector .");
        //for (int i = 0; i < vectorB.length; i++) {
        //System.out.print(String.valueOf(vectorB[i])+" ;  ");
        //}
        return Q_matrix;
    }

    public static Matrix calculateLiniarSystemQRlibrary(double matrixA[][], double vectorB[]){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]Liniar System using QR library calculus - start.");
        int dimension=vectorB.length;

        Matrix A= new Matrix(matrixA);
        Matrix b =new Matrix(vectorB,dimension);
        Matrix result=A.solve(b);
        Matrix Residual = A.times(result).minus(b);
        double rnorm = Residual.normInf();

        //System.out.println("[Log "+System.currentTimeMillis()+"][HW2]Liniar result using QR library is:");
        //for (int i = 0; i < result.getRowDimension(); i++) {
        //    System.out.print((String.valueOf(result.get(i,0))+"  ;  "));
        // }

        return result;
    }

    public static double[] calculateLiniarSystemQR(double matrixA[][], double vectorB[]){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]Liniar System calculus with algorithm - start.");
        int dimension=vectorB.length;
        double result[]=new double[dimension];

        for (int i = 0; i < dimension; i++) {
            result[i]=1;
        }

        result[vectorB.length-1]=vectorB[vectorB.length-1]/(matrixA[vectorB.length-1][vectorB.length-1]);
        for (int i = matrixA.length-2; i >=0 ; i--) {
            double sum=0;
            for (int j = i+1; j < vectorB.length ; j++) {
                sum+=matrixA[i][j]*result[j];
            }
            double product=vectorB[i]-sum;
            double value=product/matrixA[i][i];
            result[i]=value;
        }
        return result;
    }

    private static double EuclideanNorm(double vector[]) {
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]EuclideanNorm calculus - start.");
        double value = 0;
        for (double aVector : vector) {
            value += Math.pow(aVector, 2.0);
        }
        value = Math.sqrt(value);
        return value;
    }

    public static double[] mulMatrixVector(double[][] m, double[] v){
        int dimension=v.length;
        double[] res = new double[dimension];
        for (int i = 0; i < dimension; i++)
        {
            double S = 0;
            for (int j = 0; j < dimension; j++)
            {
                S += v[j] * m[i][j];
            }
            res[i]=S;
        }

        return res;
    }

    public static double[] mulMatrixVector(double[][] m, Matrix v){
        int dimension=m.length;
        double[] res = new double[dimension];
        for (int i = 0; i < dimension; i++)
        {
            double S = 0;
            for (int j = 0; j < dimension; j++)
            {
                S += v.get(j,0) * m[i][j];
            }
            res[i]=S;
        }
        return res;
    }

    public static double firstError(double[][] Ainit,double[] xHouseholder,double[] Binit){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]First error calculus - start.");
        double result[];
        double dimension=xHouseholder.length;
        result=mulMatrixVector(Ainit,xHouseholder);
        for (int i = 0; i < dimension; i++) {
            result[i]-=Binit[i];
        }
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]First error calculus result : "+String.valueOf(EuclideanNorm(result)));
        return EuclideanNorm(result);
    }

    public static double secondError(double[][] Ainit, Matrix xQR, double[] Binit){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]Second error calculus - start.");
        double result[];
        double dimension=Ainit.length;
        result=mulMatrixVector(Ainit,xQR);
        for (int i = 0; i < dimension; i++) {
            result[i]-=Binit[i];
        }
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]Second error calculus result : "+String.valueOf(EuclideanNorm(result)));
        return EuclideanNorm(result);
    }

    public static double thirdError(double[] xHouseholder,double[] s){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]Third error calculus - start.");
        double result[]=new double[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i]=xHouseholder[i]-s[i];
        }
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]Third error calculus result : "+String.valueOf(EuclideanNorm(xHouseholder)));
        return EuclideanNorm(result)/EuclideanNorm(s);
    }

    public static double fourthError(Matrix xQR,double[] s){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]Fourth error calculus - start.");
        double result[]=new double[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i]=xQR.get(i,0)-s[i];
        }
        System.out.println("[Log "+System.currentTimeMillis()+"][HW2]Fourth error calculus result : "+String.valueOf(EuclideanNorm(result)));
        return EuclideanNorm(result)/EuclideanNorm(s);
    }
}
