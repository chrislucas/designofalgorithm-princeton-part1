package impl;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * https://www.hackerrank.com/challenges/ctci-comparator-sorting/problem
 * DONE
 * */

public class ImplComparator {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);

    public static class Checker implements Comparator<Player> {
        @Override
        public int compare(Player pa, Player pb) {
            if(pa.score == pb.score) {
                return pa.name.compareTo(pb.name);
            }
            else if(pa.score < pb.score) {
                return 1;
            }
            else {
                return -1; //
            }
        }
    }

    public static class Player  {
        private String name;
        private int score;
        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return String.format("%s, %d", name, score);
        }
    }

    public static void main(String[] args) {
        try {
            int qPlayers = Integer.parseInt(reader.readLine().trim());
            Player [] players = new Player[qPlayers];
            int i = 0;
            Checker checker = new Checker();
            while (qPlayers-->0) {
                StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
                String name = tk.nextToken();
                int score = Integer.parseInt(tk.nextToken());
                players[i++] = new Player(name, score);
            }
            Arrays.sort(players, checker);
            for(Player player : players)
                writer.println(player);

        } catch (IOException e) {

        }

    }

}
