package data_structrue;

import java.util.*;

public class RPN_float {
    /* 逆波兰表达式计算四则混合运算 */

    // String: 符号  Double 优先级
    private Map<String, Integer> optMap = new HashMap<>();

    public RPN_float() {
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
                Double num = Double.parseDouble(s);
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
        String expr = "2.25*(5+(2+3*3-1)*2+3)";
        RPN_float rpn = new RPN_float();
        Double result = rpn.cal(expr);
        System.out.println(String.format("%s = %s", expr, result));
    }

    public Double cal(String expr) {
        Double result = null;
        Stack<Double> stack = new Stack<>();
        List<String> suffixExpr = toSuffixExpr(expr);
        for (String s: suffixExpr) {
            try {
                Double num = Double.parseDouble(s);
                stack.push(num);
            } catch (Exception e) {
                if (isOpt(s)) {
                    Double sum = null;
                    Double x1 = stack.pop();
                    Double x2 = stack.pop();
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
     * 当表达式中存在小数或2位以上的数时，读取的字符串无法正确解读成相应位数的数字，该方法解决了此问题
     * @param expr 运算表达式
     * @return 正确的运算表达式
     */
    private List<String> parseExpr(String expr) {
        List<String> result = new ArrayList<>();
        List<String> numList = new ArrayList<>();
        char[] chars = expr.toCharArray();
        for (char c: chars) {
            if (!isOpt(String.valueOf(c))) {
                numList.add(String.valueOf(c));
            } else {
                if (!numList.isEmpty()) {
                    String num = String.join("", numList);
                    result.add(num);
                    numList.clear();
                }
                result.add(String.valueOf(c));
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
}
