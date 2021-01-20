public class A1063330_checkpoint6_RouteLinkedList {
	private A1063330_checkpoint6_Node head;

	// Description : the constructor of leading the head Node as null.
	public A1063330_checkpoint6_RouteLinkedList() {
		this.head = null;
	}

	// Description : the constructor of input a Node as the head node.
	public A1063330_checkpoint6_RouteLinkedList(A1063330_checkpoint6_Node head) {
		this.head = head;
	}

	public void delete(int axis, int direction) {
		/*********************************
		 * The Past TODO (Checkpoint5)****************************** //TODO(Past): Input
		 * value of Node as the reference Node, // you have to delete the first Node
		 * that is same as the reference Node, // and connect the following one and the
		 * previous one.
		 * /********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/
		A1063330_checkpoint6_Node cur = head;
		A1063330_checkpoint6_Node pre = null;
		if (head == null) {
			System.out.println("list is empty");
		} else {
			while (cur.getAxis() != axis || cur.getDirection() != direction && cur.getNext() != null) {
				pre = cur;
				cur = cur.getNext();
			}
			if (cur.getAxis() == axis && cur.getDirection() == direction) {
				if (pre == null) {
					head = cur.getNext();
				} else {
					pre.setNext(cur.getNext());
				}
			}
		}

		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/
	}

	public A1063330_checkpoint6_Node search(int axis, int direction) {
		/*********************************
		 * The Past TODO (Checkpoint5)******************************** //TODO(Past):
		 * Input value of Node as the reference Node, // you have to find the first Node
		 * that is same as the reference Node, // and return it.
		 * /********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/
		A1063330_checkpoint6_Node cur = head;
		A1063330_checkpoint6_Node pre = null;
		A1063330_checkpoint6_Node node = null;
		if (head == null) {
			System.out.println("List is empty.");
		} else {
			while (cur.getAxis() != axis || cur.getDirection() != direction && cur.getNext() != null) {
				pre = cur;
				cur = cur.getNext();
			}
			if (cur.getAxis() == axis && cur.getDirection() == direction) {
				node = cur;
			}
		}
		return node;

		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/
	}

	public void insert(int referenceAxis, int referenceDirection, int axis, int direction) {
		/*********************************
		 * The TODO This Time(Checkpoint6)******************************** //TODO(1):
		 * Input value of Node as the reference Node, // and insert a Node BEFORE the
		 * first Node same as the reference Node, // and connect the following one and
		 * the previous one. //Hint The value of the Node is int variable axis and
		 * dirsction. //Hint2 If there is no reference node in linkedlist, print
		 * "Insertion null".
		 * /********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/
		A1063330_checkpoint6_Node cur = head;
		A1063330_checkpoint6_Node pre = null;
		A1063330_checkpoint6_Node node = new A1063330_checkpoint6_Node(axis, direction);
		if (head == null) {
			System.out.println("list empty");
		} else {
			while (cur.getAxis() != referenceAxis
					|| cur.getDirection() != referenceDirection && cur.getNext() != null) {
				pre = cur;
				cur = cur.getNext();
			}
			if (cur.getAxis() == referenceAxis && cur.getDirection() == referenceDirection) {
				node.setNext(cur);
				pre.setNext(node);
			} else {
				System.out.println("Insertion null");
			}
		}

		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/
	}

	public int length() {
		/*********************************
		 * The Past TODO (Checkpoint5)******************************** //TODO(Past):
		 * return how long the LinkedList is.
		 * /********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/
		int count = 0;
		A1063330_checkpoint6_Node pre = head;
		while (pre != null) {
			pre = pre.getNext();
			count++;
		}
		return count;

		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/
	}

	public void append(int axis, int direction) {
		/*********************************
		 * The Past TODO (Checkpoint5)******************************** //TODO(Past):
		 * append a Node at the end of linkedlist. //Hint The value of the Node is int
		 * variable axis and dirsction.
		 * /********************************************************************************************
		 * START OF YOUR CODE
		 ********************************************************************************************/
		A1063330_checkpoint6_Node pre = head;
		A1063330_checkpoint6_Node node = new A1063330_checkpoint6_Node(axis, direction);
		if (pre == null) {
			head = node;
		} else {
			while (pre.getNext() != null) {
				pre = pre.getNext();
			}
			pre.setNext(node);

		}

		/********************************************************************************************
		 * END OF YOUR CODE
		 ********************************************************************************************/

	}

	public A1063330_checkpoint6_Node getHead() {
		return this.head;
	}

	public void setHead(A1063330_checkpoint6_Node head) {
		this.head = head;
	}
}
