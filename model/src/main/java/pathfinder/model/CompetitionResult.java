package pathfinder.model;

import java.util.Set;

import pathfinder.model.graph.Vertex;

/**
 * @author Ernestas
 * @since 2016.11.23
 */
public class CompetitionResult {

    public static class Competitor {

        private int caloriesToExaust;
        private Set<Vertex> vertexToComplete;

        public Competitor(int caloriesToExaust, Set<Vertex> vertexToComplete) {
            this.caloriesToExaust = caloriesToExaust;
            this.vertexToComplete = vertexToComplete;
        }

        public int getCaloriesToExaust() {
            return caloriesToExaust;
        }

        public void setCaloriesToExaust(int caloriesToExaust) {
            this.caloriesToExaust = caloriesToExaust;
        }

        public Set<Vertex> getVertexToComplete() {
            return vertexToComplete;
        }

        public void setVertexToComplete(Set<Vertex> vertexToComplete) {
            this.vertexToComplete = vertexToComplete;
        }
    }

    private Competitor enemyCompetitor;
    private Competitor allyCompetitor;
    private boolean allyCompetitorWinner = true;

    public Competitor getEnemyCompetitor() {
        return enemyCompetitor;
    }

    public Competitor getAllyCompetitor() {
        return allyCompetitor;
    }

    public void setEnemyCompetitor(Competitor enemyCompetitor) {
        this.enemyCompetitor = enemyCompetitor;
    }

    public void setAllyCompetitor(Competitor allyCompetitor) {
        this.allyCompetitor = allyCompetitor;
    }

    public boolean isAllyCompetitorWinner() {
        return allyCompetitorWinner;
    }

    public void setAllyCompetitorWinner(boolean allyCompetitorWinner) {
        this.allyCompetitorWinner = allyCompetitorWinner;
    }
}
