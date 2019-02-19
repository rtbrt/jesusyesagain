package com.company;

import Jama.Matrix;

import java.util.Arrays;
import java.util.Scanner;

public class Matrice1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        double[] y=new double[n];
        double[][]x=new double[n][4];
        System.out.println("y + x1 + x2 + x3");
        for (int i=0; i<n; i++)
        {
            y[i]=in.nextDouble();
            x[i][0]=1;
            x[i][1]=in.nextDouble();
            x[i][2]=Math.pow(in.nextDouble(), 2);
            x[i][3]=Math.sin(in.nextDouble());
        }
        Matrix X = new Matrix(x);
        Matrix Y = new Matrix(y, n);
        Matrix B=Matrice1.CoFinder(X, Y);
        System.out.println(Arrays.toString(B.getRowPackedCopy()));
        System.out.println("Error="+Matrice1.Error(X.getArrayCopy(), Y.getColumnPackedCopy(), B.getColumnPackedCopy(), n));

    }
    private static Matrix CoFinder (Matrix x, Matrix y)
    {
        Matrix X = x;
        Matrix E = x.transpose();
        X=X.times(E);
        X=X.inverse();
        X=E.times(X);
        X=X.times(y);
        return X;
    }
    private static double Error(double x[][], double y[], double b[],int m)
    {
        double Y=0;
        double F=0;
        for (int i=0; i<m; i++)
        {
            Y+=y[i];
            F+=b[0]+b[1]*x[i][1]+b[2]*x[i][2]+b[3]*x[i][3];
        }
        return (Y*Y-F*F);
    }
}

