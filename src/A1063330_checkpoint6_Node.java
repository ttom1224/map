//Description : the class in order to implement LinkedList.
public class A1063330_checkpoint6_Node{
    private A1063330_checkpoint6_Node Next;
    private int direction; 
    private int axis;
    public A1063330_checkpoint6_Node(int direction, int axis){
        this.direction = direction;
        this.axis = axis;
        this.Next = null;
    }
    public void setDirection(int direction){
        /*********************************The Past TODO (Checkpoint5)******************************
        //TODO(Past) set the direction via this function.
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/
    	this.direction=direction;
         /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
    public int getDirection(){
        /*********************************The Past TODO (Checkpoint5)******************************
        //TODO(Past) get the direction via this function.
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/
    	return direction;
         
         /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
    public void setAxis(int axis){
        /*********************************The Past TODO (Checkpoint5)******************************
        //TODO(Past) set the axis via this function.
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/
    	this.axis=axis;
         
         /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
    public int getAxis(){
        /*********************************The Past TODO (Checkpoint5)******************************
        //TODO(Past) get the axis via this function.
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/
    	return axis;
         
         /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
    public void setNext(A1063330_checkpoint6_Node Next){
        /*********************************The Past TODO (Checkpoint5)******************************
        //TODO(Past) set the Next via this function.
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/
    	this.Next=Next;
         
         /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
    public A1063330_checkpoint6_Node getNext(){
        /*********************************The Past TODO (Checkpoint5)******************************
        //TODO(Past) get the Next via this function.
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/
    	return Next;
         
         /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
} 