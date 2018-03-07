import java.util.HashSet;
import java.util.Set;

/* CompliantNode refers to a node that follows the rules (not malicious)*/
public class CompliantNode implements Node {

    private boolean[]        followees;

    private Set<Transaction> pendingTransactions    = new HashSet<>();
    private Set<Transaction> additionalTransactions = new HashSet<>();
    private int              totalRounds;
    private int              currentRound           = 0;

    public CompliantNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
        this.totalRounds = numRounds;
    }

    public void setFollowees(boolean[] followees) {
        this.followees = new boolean[followees.length];
        for (int i = 0; i < followees.length; i++) {
            this.followees[i] = followees[i];
        }
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
        // IMPLEMENT THIS
        for (Transaction tx : pendingTransactions) {
            this.pendingTransactions.add(tx);
            this.additionalTransactions.add(tx);
        }
    }

    public Set<Transaction> sendToFollowers() {
        currentRound++;

        if (currentRound >= totalRounds) {
            return pendingTransactions;
        } else {
            return additionalTransactions;
        }

    }

    public void receiveFromFollowees(Set<Candidate> candidates) {

        additionalTransactions = new HashSet<>();

        for (Candidate candidate : candidates) {
            if (followees[candidate.sender] && !pendingTransactions.contains(candidate.tx)) {
                pendingTransactions.add(candidate.tx);
                additionalTransactions.add(candidate.tx);
            }
        }
    }
}
