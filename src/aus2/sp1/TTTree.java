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
 */
public class TTTree<T extends Comparable<T>>{
    private TreeNode<T> root;
    
    
    public TTTree() {            
    }
    
    public boolean insert(T paData) {
        if (root == null) {
            root = new TreeNode<T>(paData, null); 
            return true;
        }     
        
        if (this.find(root, paData) == null) {
            TreeNode<T> node = this.findLeafNode(root, paData);
            if (node == null) {
                System.out.println("pass");
            }
            if (!node.isIsThreeNode()) {
                node.setIsThreeNode(true);
                if (node.getLeftData() != null && node.getLeftData().compareTo(paData) == 1) {
                   T temp = node.getLeftData();
                   node.setLeftData(paData);
                   node.setRightData(temp);
                }
                else if (node.getRightData() != null && node.getRightData().compareTo(paData) == -1) {
                    node.setLeftData(node.getRightData());
                    node.setRightData(paData);
                }
                else {
                     node.setRightData(paData);
                }                             
              
                return true;
            }         
            else {
                TreeNode<T> helpLeft = null;
                TreeNode<T> helpRight = null;
                TreeNode<T> helpMid = null;
                while (node != null)
                {
                    T min = null;
                    T max = null;
                    T mid = null;

                    if (node.getLeftData().compareTo(paData) == -1 && node.getLeftData().compareTo(node.getRightData()) == -1) {
                        min = node.getLeftData();
                    }
                    else if (node.getLeftData().compareTo(paData) == 1 && node.getLeftData().compareTo(node.getRightData()) == 1) {
                        max = node.getLeftData();
                    }
                    else if (node.getLeftData().compareTo(paData) == 1 && node.getLeftData().compareTo(node.getRightData()) == -1 ||
                             node.getLeftData().compareTo(paData) == -1 && node.getLeftData().compareTo(node.getRightData()) == 1 ){
                        mid = node.getLeftData();
                    } 

                    if (node.getRightData().compareTo(paData) == -1 && node.getRightData().compareTo(node.getLeftData()) == -1) {
                        min = node.getRightData();
                    }
                    else if (node.getRightData().compareTo(paData) == 1 && node.getRightData().compareTo(node.getLeftData()) == 1) {
                        max = node.getRightData();
                    }
                    else if (node.getRightData().compareTo(paData) == 1 && node.getRightData().compareTo(node.getLeftData()) == -1 ||
                             node.getRightData().compareTo(paData) == -1 && node.getRightData().compareTo(node.getLeftData()) == 1 ){
                        mid = node.getRightData();
                    }

                    if (paData.compareTo(node.getRightData()) == -1 && paData.compareTo(node.getLeftData()) == -1) {
                        min = paData;
                    }
                    else if (paData.compareTo(node.getRightData()) == 1 && paData.compareTo(node.getLeftData()) == 1) {
                        max = paData;
                    }
                    else if (paData.compareTo(node.getRightData()) == 1 && paData.compareTo(node.getLeftData()) == -1 ||
                             paData.compareTo(node.getRightData()) == -1 && paData.compareTo(node.getLeftData()) == 1 ){
                        mid = paData;
                    }
                    
                    TreeNode<T> nodeLeft = new TreeNode<T>(min, null);   
                    TreeNode<T> nodeRight = new TreeNode<T>(max, null);
                   
                    /*if (paData == min && helpMid != null) {
                        nodeLeft = helpMid;
                        helpMid = null;
                        
                    
                    }*/
                    
                   /* if (helpRight != null) {
                        nodeRight = helpRight;
                        nodeRight.setLeftData(nodeRight.getRightData());
                        nodeRight.setRightData(null);
                        nodeRight.setLeftSon(nodeRight.getCenterSon());
                        nodeRight.setCenterSon(null);
                        nodeRight.setParent(helpMid);
                        System.out.println("Pas");
                        helpRight = null;
                    }*/
                       
                    if (node == root) {      
                        root = new TreeNode<T>(mid, null); 
                        root.setLeftSon(nodeLeft);
                        root.setRightSon(nodeRight);
                        nodeLeft.setParent(root);
                        nodeRight.setParent(root);
                       
                        return true;
                    }
                    else 
                    {         
                        if (!node.getParent().isThreeNode) {
                            node.getParent().setRightData(mid);
                            node.getParent().setCenterSon(nodeLeft);
                            node.getParent().setRightSon(nodeRight);
                            nodeLeft.setParent(node.getParent());
                            nodeRight.setParent(node.getParent());
                            node.getParent().setIsThreeNode(true);                             
                            return true;
                        }
                        else {     
                            TreeNode<T> nodeMid = new TreeNode<T>(mid, null);                            
                            nodeMid.setLeftSon(nodeLeft);
                            nodeMid.setRightSon(nodeRight);
                            nodeLeft.setParent(nodeMid);
                            nodeRight.setParent(nodeMid); 
                            node = node.getParent();                             
                            paData = nodeMid.getLeftData();                            
                            helpMid = nodeMid;
                            helpRight = node;
                        }
                    }
                }
                    
            }
            
        }
        
        return false;
    }
    
    
    public TreeNode<T>  find(TreeNode<T> paNode, T paData) {
        
        TreeNode<T> temp = paNode;        
        while(temp != null) {            
            if (temp.getLeftData() == paData || temp.getRightData() == paData) {
                return temp;
            }
            else if (temp.getLeftSon() != null && temp.getLeftData() !=null && paData.compareTo(temp.getLeftData()) == -1) {                
                temp = temp.getLeftSon();                               
            }        
            else if (temp.isThreeNode && paData.compareTo(temp.getLeftData()) == 1 && paData.compareTo(temp.getRightData()) == -1 && temp.getCenterSon() != null) {
                temp = temp.getCenterSon();
            }
            else if (temp.getRightData() != null && temp.getRightSon() != null && paData.compareTo(temp.getRightData()) == 1) {                
                temp = temp.getRightSon();
            }      
            else {
                temp = null;
            }
        }        
        return temp;
    }
    
    public TreeNode<T> findLeafNode(TreeNode<T> paNode, T paData) {  
        
        TreeNode<T> temp = paNode;        
        while(temp != null) {
            
            if (temp.getLeftSon() == null && temp.getRightSon() == null) {                
                return temp;
            }        
            else if (temp.getLeftData() != null && paData.compareTo(temp.getLeftData()) == -1) {
                temp = temp.getLeftSon();                               
            }       
            else if (paData.compareTo(temp.getLeftData()) == 1) {
                temp = temp.getRightSon();
            }
            else if (temp.isThreeNode && paData.compareTo(temp.getLeftData()) == 1 && paData.compareTo(temp.getRightData()) == -1) {
                temp = temp.getCenterSon();
            }
            else if (temp.getRightData() != null && paData.compareTo(temp.getRightData()) == 1 ) {                
                temp = temp.getRightSon();
            }                        
        }        
        return temp;
    }  
    
    
    public boolean delete (T paData) {
         TreeNode<T> node = this.findLeafNode(root, paData);
         
        if (node != null) {
            if (node.isLeaf() && node == root) {
            
                
                
            }
        }
         
        
        
        return false;
    }
    
    
}
