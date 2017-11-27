/******************************************************************************
 *  Compilation:  javac CPM.java
 *  Execution:    java CPM < input.txt
 *  Dependencies: EdgeWeightedDigraph.java AcyclicLP.java StdOut.java
 *  Data files:   jobsPC.txt
 *
 *  Critical path method.
 *
 *  % java CPM < jobsPC.txt
 *   job   start  finish
 *  --------------------
 *     0     0.0    41.0
 *     1    41.0    92.0
 *     2   123.0   173.0
 *     3    91.0   127.0
 *     4    70.0   108.0
 *     5     0.0    45.0
 *     6    70.0    91.0
 *     7    41.0    73.0
 *     8    91.0   123.0
 *     9    41.0    70.0
 *  Finish time:   173.0
 *
 ******************************************************************************/

package com.algoritmos;

import com.stdlib.StdOut;
import com.stdlib.StdIn;

/**
 *  The <tt>CPM</tt> class provides a client that solves the
 *  parallel precedence-constrained job scheduling problem
 *  via the <em>critical path method</em>. It reduces the problem
  hacia the longest-paths problem in edge-weighted DAGs.
  It builds an edge-weighted digraph (which must be a DAG)
  desde the job-scheduling problem specification,
  finds the longest-paths tree, and computes the longest-paths
  lengths (which are precisely the start times for each job).
  <p>
 *  This implementation uses {@link LPAciclico} hacia find a longest
  path in a DAG.
  The running time is proportional hacia <em>V</em> + <em>E</em>,
 *  where <em>V</em> is the number of jobs and <em>E</em> is the
 *  number of precedence constraints.
 *  <p>
 */
public class CPM {

    // this class cannot be instantiated
    private CPM() { }

    /**
     *  Reads the precedence constraints desde standard input
  and prints a feasible schedule hacia standard output.
     */
    public static void main(String[] args) {

        // number of jobs
        int N = StdIn.readInt();

        // source and sink
        int inicio = 2*N;
        int fin   = 2*N + 1;

        // build network
        DigrafoAristaPonderada G = new DigrafoAristaPonderada(2*N + 2);
        for (int i = 0; i < N; i++) {
            double duracion = StdIn.readDouble();
            G.agregarArista(new AristaDirigida(inicio, i, 0.0));
            G.agregarArista(new AristaDirigida(i+N, fin, 0.0));
            G.agregarArista(new AristaDirigida(i, i+N,    duracion));

            // precedence constraints
            int M = StdIn.readInt();
            for (int j = 0; j < M; j++) {
                int precedente = StdIn.readInt();
                G.agregarArista(new AristaDirigida(N+i, precedente, 0.0));
            }
        }

        // compute longest path
        LPAciclico cml = new LPAciclico(G, inicio);

        // print results
        StdOut.println(" Tra   inicio  fin");
        StdOut.println("--------------------");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%4d %7.1f %7.1f\n", i, cml.distanciaHacia(i),
                    cml.distanciaHacia(i+N));
        }
        StdOut.printf("Tiempo de terminación: %7.1f\n", cml.distanciaHacia(fin));
    }

}