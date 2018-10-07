import java.util.Comparator;


public class CompareRoomVisitor implements Comparator<FootTrafficVO> {
	
	@Override
	public int compare(FootTrafficVO record1, FootTrafficVO record2) {
		int c;
	    c = record1.getRoom() < record2.getRoom() ? -1 : record1.getRoom() > record2.getRoom() ? +1 : 0;
	    if (c == 0)
	    	 c = record1.getVisitorNo()< record2.getVisitorNo() ? -1 : record1.getVisitorNo() > record2.getVisitorNo() ? +1 : 0;
	    if (c == 0)
	       c = record1.getVisitorStatus().compareTo(record2.getVisitorStatus());
	    return c;
	}

	


}
