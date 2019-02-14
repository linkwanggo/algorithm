package data_structrue;

import java.util.*;

public class RPN {
    /* 逆波兰表达式计算四则混合运算 */

    // String: 符号  Integer 优先级
    private Map<String, Integer> optMap = new HashMap<>();

    public RPN() {
        // 初始化符号Map
        optMap.put("+", 1<<1);
        optMap.put("-", 1<<1);
        optMap.put("*", 1<<2);
        optMap.put("/", 1<<2);
        optMap.put("(", 1);
        optMap.put(")", 1<<10);
    }

    /**
     * 将中缀表达式转为后缀表达式
     * @param expr 字符串表达式
     * @return 后缀表达式
     */
    private List<String> toSuffixExpr(String expr) {
        Stack<String> stack = new Stack<>();
        List<String> exprList = parseExpr(expr);
        List<String> result = new ArrayList<>();
        for (String s: exprList) {
            try {
                int num = Integer.parseInt(s);
                result.add(String.valueOf(num));
            } catch (Exception e) {
                // 右括号
                if (")".equals(s)) {
                    while (!"(".equals(stack.peek())) {
                        result.add(stack.pop());
                    }
                    stack.pop(); // 删掉 "("
                    continue;
                }
                if ("(".equals(s)) {
                    stack.push(s);
                    continue;
                }
                if (isOpt(s)) {
                    while (!stack.isEmpty() && isLowLevel(s, stack.peek())) {
                        result.add(stack.pop());
                    }
                    stack.push(s);
                }
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        String expr = "2*(5+(2+3*3-1)*2+3)";
        RPN rpn = new RPN();
        System.out.println(rpn.toSuffixExpr(expr));
        Integer result = rpn.cal(expr);
        System.out.println(result);
    }

    public Integer cal(String expr) {
        Integer result = null;
        Stack<Integer> stack = new Stack<>();
        List<String> suffixExpr = toSuffixExpr(expr);
        for (String s: suffixExpr) {
            try {
                int num = Integer.parseInt(s);
                stack.push(num);
            } catch (Exception e) {
                if (isOpt(s)) {
                    Integer sum = null;
                    int x1 = stack.pop();
                    int x2 = stack.pop();
                    if ("+".equals(s)) {
                        sum = x2 + x1;
                    } else if ("-".equals(s)) {
                        sum = x2 - x1;
                    } else if ("*".equals(s)) {
                        sum = x2 * x1;
                    } else if("/".equals(s)) {
                        sum = x2 / x1;
                    }
                    stack.push(sum);
                }
            }
        }
        if (!stack.isEmpty() && stack.size() == 1) {
            result = stack.pop();
        } else {
            throw new RuntimeException("结果出错" + stack.toString());
        }
        return result;
    }

    /**
     * 当表达式中存在大于2位数的数时，读取的字符串无法正确解读成相应位数的数字，该方法解决了此问题
     * @param expr 运算表达式
     * @return 正确的运算表达式
     */
    private List<String> parseExpr(String expr) {
        List<String> result = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        char[] chars = expr.toCharArray();
        for (char c: chars) {
            try {
                int num = Integer.parseInt(String.valueOf(c));
                numList.add(num);
            } catch (Exception e) {
                StringBuilder cacheNum = new StringBuilder();
                for (Integer i: numList) {
                    cacheNum.append(String.valueOf(i));
                }
                if (!cacheNum.toString().isEmpty()) {
                    result.add(cacheNum.toString());
                }
                result.add(String.valueOf(c));
                numList.clear();
            }
        }
        if (!numList.isEmpty()) {
            for (Integer i: numList) {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }

    private boolean isOpt(String s) {
        return optMap.containsKey(s);
    }

    private boolean isLowLevel(String s, String top) {
        return optMap.get(s) <= optMap.get(top);
    }

    private boolean isNumeric(String s) {
        try {
            int num = Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
