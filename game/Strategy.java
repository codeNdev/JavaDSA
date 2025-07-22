package game;

public interface Strategy {
    String throwHand(String[] player, String[] opponent, int handNumber) throws Exception;
}
