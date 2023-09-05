package boggle;

import java.util.HashSet;
import java.util.Set;

/**
 * BoggleScoreBoard stores statistics related to Boggle gameplay.
 */
public class BoggleScoreBoard {

    /**
     * The set of words found by the player in the current round.
     */
    private Set<String> playerWords;

    /**
     * The set of words found by the computer in the current round.
     */
    private Set<String> computerWords;

    /**
     * The player's score for the current round.
     */
    private int pScore;

    /**
     * The computer's score for the current round.
     */
    private int cScore;

    /**
     * The player's total score across every round.
     */
    private int pScoreTotal;

    /**
     * The computer's total score across every round.
     */
    private int cScoreTotal;

    /**
     * The average number of words found by the player per round.
     */
    private double pAverageWords;

    /**
     * The average number of words found by the computer per round.
     */
    private double cAverageWords;

    /**
     * The current round being played.
     */
    private int round;

    /**
     * Enumerates types of players (human or computer).
     */
    public enum Player {
        Human(),
        Computer();

        Player() {
        }
    }

    /**
     * BoggleScoreBoard constructor.
     * Sets round, totals, and averages to 0.
     * Initializes word lists (which are sets) for computer and human players.
     */
    public BoggleScoreBoard() {
        this.round = 0;
        this.pScore = 0;
        this.cScore = 0;
        this.pScoreTotal = 0;
        this.cScoreTotal = 0;
        this.pAverageWords = 0;
        this.cAverageWords = 0;
        this.playerWords = new HashSet<>();
        this.computerWords = new HashSet<>();
    }

    /**
     * Add a word to a given player's word list for the current round.
     * Increment the player's score as words are added.
     *
     * @param word   The word to be added to the list.
     * @param player The player to whom the word was awarded.
     */
    public void addWord(String word, Player player) {
        int score = 0;
        for (int i = 1; i <= word.length(); i++) {
            if (i >= 4) {
                score += 10;
            }
        }
        switch (player) {
            case Human -> {
                this.playerWords.add(word);
                this.pScore += score;
            }
            case Computer -> {
                this.computerWords.add(word);
                this.cScore += score;
            }
        }
    }

    public void setPlayerScore(int score) {
        this.pScore = score;
    }

    /**
     * End a given round.
     * Clear out the human and computer word lists to begin again.
     * Update each player's total scores, average scores, and reset current scores to zero.
     * Increment the current round number by 1.
     */
    public void endRound() {
        this.pAverageWords = (this.playerWords.size() + this.pAverageWords) / (getRound() + 1);
        this.cAverageWords = (this.computerWords.size() + this.cAverageWords) / (getRound() + 1);
        this.playerWords = new HashSet<>();
        this.computerWords = new HashSet<>();
        this.pScoreTotal += this.pScore;
        this.cScoreTotal += this.cScore;
        this.pScore = 0;
        this.cScore = 0;
        this.round += 1;
    }

    /**
     * Get the player's word list.
     *
     * @return The player's word list.
     */
    public Set<String> getPlayerWords() {
        return this.playerWords;
    }

    /**
     * Get the computer's word list.
     *
     * @return The computer's word list.
     */
    public Set<String> getComputerWords() {
        return this.computerWords;
    }

    /**
     * Get the number of rounds played.
     *
     * @return The number of rounds played.
     */
    public int getRound() {
        return this.round;
    }

    /**
     * Get the current player score.
     *
     * @return The current player score.
     */
    public int getScore() {
        return this.pScore;
    }

    public int getpScore() {
        return pScore;
    }

    public int getpScoreTotal() {
        return pScoreTotal;
    }

    public int getcScore() {
        return cScore;
    }

    public int getcScoreTotal() {
        return this.cScoreTotal;
    }

    public int getpwordCount() {
        return playerWords.size();
    }

    public int getcwordcount() {
        return computerWords.size();
    }

    public double getApwordCount() {
        return this.pAverageWords;
    }

    public double getAcwordcount() {
        return this.cAverageWords;
    }

}
