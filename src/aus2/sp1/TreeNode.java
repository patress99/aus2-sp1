/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aus2.sp1;

/**
 *
 * @author Pato
 * @param <T>
 * @param <E>
*/
 
public class TreeNode<T extends Comparable<T>> {
    private T leftData;   
    private T rightData;   
    private T helpVariable;

    

    public boolean isThreeNode; 
    public boolean isLeaf;

    
    private TreeNode<T> leftSon;
    private TreeNode<T> rightSon;
    private TreeNode<T> centerSon;
    private TreeNode<T> parent;    
    
    public TreeNode() { 
            this.leftSon = null;
            this.rightSon = null;
            this.centerSon = null;    
    }
    
    public TreeNode(T paLeftData,  T paRightData) {
        this.leftData = paLeftData;
        this.rightData = paRightData;
        this.isThreeNode = false;
    }    
    
    public boolean isLeaf() {
        return this.leftSon == null;
    }
    
    public TreeNode<T> getLeftSon() {
        return this.leftSon;        
    }
    
    public TreeNode<T> getRightSon() {
        return this.rightSon;        
    }
    
    public TreeNode<T> getCenterSon() {
        return this.centerSon;        
    }
    
    
    public void setRightSon(TreeNode<T> paRight) {
        this.rightSon = paRight;
    }
    
    public void setLeftSon(TreeNode<T> paLeft) {
        this.leftSon = paLeft;
    }
    
    public void setCenterSon(TreeNode<T> paCenter) {
        this.centerSon = paCenter;
    }
    
    
     public T getLeftData() {
        return this.leftData;
    }

    public void setLeftData(T paLeftData) {
        this.leftData = paLeftData;
    }

    public T getRightData() {
        return this.rightData;
    }

    public void setRightData(T paRightData) {
        this.rightData = paRightData;
    }
    
    
    public boolean isIsThreeNode() {
        return this.isThreeNode;
    }

    public void setIsThreeNode(boolean paIsThreeNode) {
        this.isThreeNode = paIsThreeNode;
    }
    
    
    public TreeNode<T> getParent() {
        return this.parent;
    }

    public void setParent(TreeNode<T> paParent) {
        this.parent = paParent;
    }
          
    public T getHelpVariable() {
        return helpVariable;
    }

    public void setHelpVariable(T helpVariable) {
        this.helpVariable = helpVariable;
    }
    
    public boolean isIsLeaf() {
        if (this.getRightSon() == null && this.getCenterSon() == null && this.getLeftSon() == null) {
            return true;
        }
        else 
        {
            return false;
        }
        
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
 
}
