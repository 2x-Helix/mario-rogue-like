package game.ground;

public class Wall extends HighGround {

	public Wall() {
		super('#');
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

}
