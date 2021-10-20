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
            TreeNode<T> node = this.findLeafNode( paData);
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
                boolean fromR = false;
                boolean fromL = false;
                boolean fromM = false;
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
                   TreeNode<T> nodeMid = new TreeNode<T>(mid, null);   
                   
                   
                    if (fromR) {
                        nodeRight.setRightSon(node.getRightSon().getRightSon());
                        nodeRight.setLeftSon(node.getRightSon().getLeftSon());
                        nodeRight.getRightSon().setParent(nodeRight);
                        nodeRight.getLeftSon().setParent(nodeRight);
                        
                        nodeLeft.setLeftSon(node.getLeftSon());
                        nodeLeft.setRightSon(node.getCenterSon());
                        nodeLeft.getLeftSon().setParent(nodeLeft);
                        nodeLeft.getRightSon().setParent(nodeLeft);
                        
                    }
                    else if (fromM) {
                        nodeRight.setRightSon(node.getRightSon());
                        nodeRight.setLeftSon(node.getCenterSon().getRightSon());
                        nodeRight.getRightSon().setParent(nodeRight);
                        nodeRight.getLeftSon().setParent(nodeRight);
                        
                        nodeLeft.setRightSon(node.getCenterSon().getLeftSon());
                        nodeLeft.setLeftSon(node.getLeftSon());
                        nodeLeft.getRightSon().setParent(nodeLeft);
                        nodeLeft.getLeftSon().setParent(nodeLeft);
                    }
                    else if (fromL) {
                        nodeRight.setRightSon(node.getRightSon());
                        nodeRight.setLeftSon(node.getCenterSon());
                        nodeRight.getRightSon().setParent(nodeRight);
                        nodeRight.getLeftSon().setParent(nodeRight);
                        
                        nodeLeft.setLeftSon(node.getLeftSon().getLeftSon());
                        nodeLeft.setRightSon(node.getLeftSon().getRightSon());
                        nodeLeft.getRightSon().setParent(nodeLeft);
                        nodeLeft.getLeftSon().setParent(nodeLeft);
                        
                        
                        
                    }
                       
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
                            if (node.getParent().getLeftData().compareTo(mid) == 1) {
                                T ll = node.getParent().getLeftData();
                                node.getParent().setLeftData(mid);
                                node.getParent().setRightData(ll);
                                node.getParent().setLeftSon(nodeLeft);
                                node.getParent().setCenterSon(nodeRight);
                                nodeLeft.setParent(node.getParent());
                                nodeRight.setParent(node.getParent());                                
                            }
                            else 
                            {
                                node.getParent().setRightData(mid);
                                node.getParent().setCenterSon(nodeLeft);
                                node.getParent().setRightSon(nodeRight);
                                nodeLeft.setParent(node.getParent());
                                nodeRight.setParent(node.getParent());
                            }      
                            node.getParent().setIsThreeNode(true);                             
                            return true;
                        }
                        else {     
                                                     
                            nodeMid.setLeftSon(nodeLeft);
                            nodeMid.setRightSon(nodeRight);
                            nodeLeft.setParent(nodeMid);
                            nodeRight.setParent(nodeMid); 
                            nodeMid.setParent(node.getParent());
                                   
                            
                            paData = nodeMid.getLeftData();                            
                            
                            if (mid.compareTo(node.getParent().getRightData()) == 1) {
                                fromR = true;
                                node.getParent().setRightSon(nodeMid);
                            }
                            else if (mid.compareTo(node.getParent().getRightData()) == -1 && mid.compareTo(node.getParent().getLeftData()) == 1) {
                                
                                fromM = true;
                                node.getParent().setCenterSon(nodeMid);
                            }
                            else if (mid.compareTo(node.getParent().getLeftData()) == -1) {
                                fromL = true;
                                node.getParent().setLeftSon(nodeMid);
                                        
                            }
                            
                            node = node.getParent();   
                            
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
    
    public TreeNode<T> findLeafNode( T paData) {  
        
        TreeNode<T> temp = root;        
        while(temp != null) {
            
            if (temp.getLeftSon() == null && temp.getRightSon() == null && temp.getCenterSon() == null) {                
                return temp;
            }        
            else if (temp.getLeftData() != null && paData.compareTo(temp.getLeftData()) == -1) {
                temp = temp.getLeftSon();                               
            }    
            else if (temp.isIsThreeNode() && paData.compareTo(temp.getLeftData()) == 1 && paData.compareTo(temp.getRightData()) == -1) {
                temp = temp.getCenterSon();
            }
            else if (temp.getLeftData() != null && paData.compareTo(temp.getLeftData()) == 1) {
                temp = temp.getRightSon();
            }            
            else if (temp.getRightData() != null && paData.compareTo(temp.getRightData()) == 1 ) {                
                temp = temp.getRightSon();
            }                        
        }        
        return temp;
    }    
    
    
    public boolean delete (T paData) {
         TreeNode<T> node = this.findLeafNode( paData);
         
        if (node != null) {
            if (node.isLeaf() && node == root) {
            
                
                
            }
        }
         
        
        
        return false;
    }
    
    
}