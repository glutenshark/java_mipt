package HT;

public class Zadanie_2 {
    // Определение узла бинарного дерева (из задания).
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Хочу вычислить максимальную глубину дерева.
    // Буду делать рекурсией: глубина = 1 + макс(глубина слева, глубина справа).
    public static int maxDepth(TreeNode koren) {
        // Если узел пустой (ну null), глубина 0.
        if (koren == null) {
            return 0;
        }
        // Рекурсивно вычисляю глубину для левой и правой ветки.
        int glubinaLeft = maxDepth(koren.left);
        int glubinaRight = maxDepth(koren.right);
        // Ага, сравниваю, где глубже.
        if (glubinaLeft > glubinaRight) {
            return glubinaLeft + 1;  // слева глубже, добавляю текущий узел (+1)
        } else {
            return glubinaRight + 1; // справа глубже (или равны), учитываю текущий узел
        }
    }

    public static void main(String[] args) {
        // Создаю небольшое тестовое дерево:
        //       1
        //      / \
        //     2   4
        //    /
        //   3
        TreeNode tree = new TreeNode(1,
                            new TreeNode(2, new TreeNode(3), null),
                            new TreeNode(4)
                        );
        // Проверяю глубину этого дерева.
        System.out.println(maxDepth(tree)); // ожидаю глубину = 3 (1->2->3 самая длинная цепочка)
    }
}

