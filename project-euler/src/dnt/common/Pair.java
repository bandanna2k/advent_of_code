package dnt.common;

public class Pair<L, R>
{
    private L left;
    private R right;

    public Pair(L left, R right)
    {
        this.left = left;
        this.right = right;
    }

    public L getLeft()
    {
        return left;
    }

    public R getRight()
    {
        return right;
    }

    public Pair<L, R> setLeft(L left)
    {
        this.left = left;
        return this;
    }

    public Pair<L, R> setRight(R right)
    {
        this.right = right;
        return this;
    }

    @Override
    public String toString()
    {
        return "dnt.maths.Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
