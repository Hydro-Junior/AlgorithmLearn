package com.xjy.basic;


/**
 * @Author: Mr.Xu
 * @Date: Created in 9:18 2019/3/21
 * @Description:红黑树个人版
 * reference：TreeMap source code
 */
public class MyRedBlackTree<K extends Comparable<? super K>, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private Node<K,V> root;
    private static class Node<K,V>{
        K key;
        V value;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;
        boolean color = BLACK;
        Node(K key, V value, Node<K,V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
    }
    public MyRedBlackTree(){
        root = null;
    }
    public V get(K key){
        Node<K,V> node = get(key,root);
        if(node != null) return node.value;
        else return null;
    }
    private Node<K,V> get(K key, Node<K,V> node){
        if(node == null) return null;
        if(key.compareTo(node.key) < 0){
            return get(key, node.left);
        }else if(key.compareTo(node.key) > 0){
            return get(key, node.right);
        }else return node;
    }

    /**
     * 几个访问域的方法，避免null带来的不安全访问
     */
    private static <K,V> boolean colorOf(Node<K,V> p) {
        return (p == null ? BLACK : p.color);
    }
    private static <K,V> Node<K,V> parentOf(Node<K,V> p) {
        return (p == null ? null: p.parent);
    }
    private static <K,V> void setColor(Node<K,V> p, boolean c) {
        if (p != null)
            p.color = c;
    }
    private static <K,V> Node<K,V> leftOf(Node<K,V> p) {
        return (p == null) ? null: p.left;
    }
    private static <K,V> Node<K,V> rightOf(Node<K,V> p) {
        return (p == null) ? null: p.right;
    }
    /**
     * 红黑树的左旋，与AVL不同的是牵涉到父节点的变化，要小心对待
     * 普通的左旋
     * Node tmp = p.right;
     * p.right = tmp.left;
     * tmp.left = p;
     * AVL调整高度 p.height = max(..)+1
     * t.height = max(..)+1
     */
    private void rotateLeft(Node<K,V> p){

        if(p != null){
            Node<K,V> r = p.right;
            p.right = r.left;
            //r.left的父亲发生了变化，要调整
            if(r.left != null){
                r.left.parent = p;
            }
            //r代替p的位置，所以r的父亲是p原来的父亲
            r.parent = p.parent;
            //替换p原来的父亲的儿子
            if(p.parent == null){ //p原来是根节点
                root = r;
            }else if(p.parent.left == p){ //p是左孩子
                p.parent.left = r;
            }else //p是右孩子
                p.parent.right = r;
            r.left = p; //原来旋转的最后一步移到此处
            p.parent = r; //p的父亲变为r
        }
    }

    /**
     * 红黑树的右旋
     * @param p
     */
    private void rotateRight(Node<K,V> p){
        if(p != null){
            Node<K,V> l = p.left;
            //l.right的父子关系
            p.left = l.right;
            if(l.right != null){
                l.right.parent = p;
            }
            //l的父子关系
            l.parent = p.parent;
            if(p.parent == null){
                root = l;
            }else if(p.parent.left == p){
                p.parent.left = l;
            }else p.parent.right = l;
            //p的父子关系
            l.right = p;
            p.parent = l;
        }
    }
    public void put(K key, V value){
        Node<K,V> t = root;
        if(t == null){
            root = new Node(key,value,null);
            return;
        }
        Node<K,V> parent;
        int cmp;
        do{
            parent = t;
            cmp = key.compareTo(t.key);
            if(cmp < 0){
                t = t.left;
            }else if(cmp > 0){
                t = t.right;
            }else {
                t.value = value; return;
            }
        }while(t != null);
        Node<K,V> e = new Node<>(key,value,parent);
        if(cmp < 0){
            parent.left = e;
        }else parent.right = e;
        fixAfterInsertion(e);
    }

    /**
     * 红黑树插入后调整，叔叔节点是关键
     * @param x
     */
    private void fixAfterInsertion(Node<K,V> x){
        x.color = RED;
        while(x != null && x != root && x.parent.color == RED){//x不为空，x不是根节点，父节点为红才调整
            if(parentOf(x) == leftOf(parentOf(parentOf(x)))){//如果x的爸爸是左儿子
                Node<K,V> y = rightOf(parentOf(parentOf(x))); //叔叔节点
                if(colorOf(y) == RED){//叔叔节点为红
                    setColor(parentOf(x),BLACK); //父节点设为黑色
                    setColor(y,BLACK);//叔叔节点设为黑色
                    setColor(parentOf(parentOf(x)),RED);//祖父节点设置为红色（这样一来黑高不变）
                    x = parentOf(parentOf(x));//x变成祖父节点继续调整
                }else {//叔叔节点为黑,可以用来周转
                    if(x == rightOf(parentOf(x))){//x是右儿子（父亲是左儿子），左旋
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x),BLACK); //把父亲设为黑
                    setColor(parentOf(parentOf(x)),RED);//把祖父节点设为红
                    rotateRight(parentOf(parentOf(x)));//右旋祖父节点
                }
            }else{//对称处理
                Node<K,V> y = leftOf(parentOf(parentOf(x)));
                if(colorOf(y) == RED){
                    setColor(y,BLACK);
                    setColor(parentOf(x),BLACK);
                    setColor(parentOf(parentOf(x)),RED);
                    x = parentOf(parentOf(x));
                }else {
                    if(x == leftOf(parentOf(x))){
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x),BLACK);
                    setColor(parentOf(parentOf(x)),RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
    }
    public V remove(K key){
        Node<K,V> p = get(key,root);
        if(p == null) return null;
        V oldValue = p.value;
        deleteEntry(p);
        return oldValue;
    }
    private void deleteEntry(Node<K,V> p){
        //先更换实际要删除的结点
        if(p.left != null && p.right != null){
            Node<K,V> s = successor(p);
            p.key = s.key;
            p.value = s.value;
            p = s;
        }
        //确定要替代的节点
        Node<K,V> replacement = (p.left != null ? p.left : p.right); //优先用左子节点替换要删除的节点
        if(replacement != null){
            replacement.parent = p.parent;
            if(p.parent == null){
                root = replacement;
            }else if(p == p.parent.left){
                p.parent.left = replacement;
            }else{
                p.parent.right = replacement;
            }
            p.left = p.right = p.parent = null;
            if(p.color == BLACK)
                fixAfterDeletion(replacement);//replacement的关系环境，就是被删掉的p的关系环境
        }else if(p.parent == null){// p是根节点
            root = null;
        }else { //p是叶子节点但不是根节点
            if(p.color == BLACK){
                fixAfterDeletion(p);
            }
            //如果调整后p的父节点不为空，把p断开连接
            if(p.parent != null){
                if(p == p.parent.left) p.parent.left = null;
                else if(p == p.parent.right) p.parent.right = null;
                p.parent = null;
            }
        }
    }

    /**
     * 红黑树删除后调整,兄弟节点是关键
     * @param x
     */
    private void fixAfterDeletion(Node<K, V> x) {
        while(x != root && colorOf(x) == BLACK){//x不是根节点且为黑色
            if(x == leftOf(parentOf(x))){//如果x是左子节点
                Node<K,V> sib = rightOf(parentOf(x));

                if(colorOf(sib) == RED){ // 兄弟节点为红（父节点肯定为黑）
                    setColor(sib,BLACK);//把兄弟节点设为黑
                    setColor(parentOf(x), RED);//把父亲节点设为红
                    rotateLeft(parentOf(x)); //左旋父节点
                    sib = rightOf(parentOf(x)); //更换兄弟节点（sib.left）黑色
                }

                if(colorOf(leftOf(sib)) == BLACK &&
                   colorOf((rightOf(sib))) == BLACK){
                    setColor(sib,RED);
                    x = parentOf(x);//新的x节点，向上追溯
                } else {
                    if (colorOf(rightOf(sib)) == BLACK) {//如果兄弟节点的左孩子为红
                        setColor(leftOf(sib), BLACK);//把兄弟节点的左孩子设为黑色
                        setColor(sib, RED);//兄弟节点颜色置为红色
                        rotateRight(sib);//右旋
                        sib = rightOf(parentOf(x));//兄弟节点的左孩子成为新的Sib
                    }
                    setColor(sib, colorOf(parentOf(x)));//把S的颜色设为父节点的颜色
                    setColor(parentOf(x), BLACK);//将父节点设为黑色
                    setColor(rightOf(sib), BLACK);//将兄弟节点的右孩子设为黑色
                    rotateLeft(parentOf(x));//左旋父节点，完成补黑操作
                    x = root;//将x设为根节点
                }
            } else { // symmetric
                Node<K,V> sib = leftOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }

                if (colorOf(rightOf(sib)) == BLACK &&
                        colorOf(leftOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }
    }

    //获取下一个节点
    private static <K,V> Node<K,V> successor(Node<K,V> t){
        if(t == null) return null;
        else if(t.right != null) {
            Node<K,V> p = t.right;
            while(p.left != null){
                p = p.left;
            }
            return p;
        }else {//在这个类中这个方法只用于delete，这个部分不太需要
            Node<K,V> p = t.parent;
            Node<K,V> ch = t;
            while(p != null && ch == p.right){//ch是右儿子的情况下
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }
}
