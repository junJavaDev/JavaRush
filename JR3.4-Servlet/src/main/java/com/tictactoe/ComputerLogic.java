package com.tictactoe;

import java.util.List;
import java.util.Set;

import static com.tictactoe.Sign.*;

public class ComputerLogic {
    private ComputerLogic() {
    }

    public static int getMove(Field field, Sign playerSign) {
        Sign computerSign = playerSign == CROSS ? NOUGHT : CROSS;
        Set<Integer> playerSigns = field.getIndexes(playerSign);
        Set<Integer> computerSigns = field.getIndexes(computerSign);

        List<Sign> data = field.getFieldData();

        if (data.get(4) == EMPTY) return 4;

        boolean twoCrosses = playerSign == CROSS
                && playerSigns.size() == 2;

        if (twoCrosses
                && playerSigns.contains(2)
                && playerSigns.contains(6)) return 1;

        if (twoCrosses
                && playerSigns.contains(4)
                && playerSigns.contains(8)) return 2;

        if (twoCrosses
                && playerSigns.contains(5)
                && playerSigns.contains(7)) return 8;

        if (playerSign == NOUGHT && playerSigns.size() == 1 && playerSigns.contains(0)) return 2;

        for (List<Integer> winPossibility : field.getWinPossibilities()) {

            Integer pcWinIndex = checkThirdSign(field, computerSigns, winPossibility);
            if (pcWinIndex != null) return pcWinIndex;

            Integer pcNonLoseIndex = checkThirdSign(field, playerSigns, winPossibility);
            if (pcNonLoseIndex != null) return pcNonLoseIndex;

        }
        return field.getEmptyFieldIndex();
    }

    private static Integer checkThirdSign(Field field, Set<Integer> signs, List<Integer> winPossibility) {
        Integer winP1 = winPossibility.get(0);
        Integer winP2 = winPossibility.get(1);
        Integer winP3 = winPossibility.get(2);

        boolean pc1 = signs.contains(winP1);
        boolean pc2 = signs.contains(winP2);
        boolean pc3 = signs.contains(winP3);
        if (pc1 && pc2 && isEmpty(field, winP3)) return winP3;
        if (pc1 && pc3 && isEmpty(field, winP2)) return winP2;
        if (pc2 && pc3 && isEmpty(field, winP1)) return winP1;
        return null;
    }

    private static boolean isEmpty(Field field, int index) {
        return field.getField().get(index) == EMPTY;
    }
}
