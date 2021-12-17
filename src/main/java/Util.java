import java.util.Arrays;
import java.util.List;

public class Util {
    public static boolean isOperator(String token) {
        List<String> operatorList = Arrays.asList("+", "*", "!", "(", ")");
        return operatorList.contains(token);
    }
    public static int calculate(int v1, int v2, String operator) {
        switch (operator) {
            case "+":
                if (v1 == 0 && v2 == 0) {
                    return 0;
                } else if (v1 == 1 || v2 == 1) {
                    return 1;
                }
                return -1;
            case "*":
                if (v1 == 0 || v2 == 0) {
                    return 0;
                } else if (v1 == 1 && v2 == 1) {
                    return 1;
                }
                return -1;
            default:
                return 0;
        }
    }
}
