package pac;

public class Key {
	Boolean Active = false;
	Boolean Black = false;

	ParticleSystem ps;

	int Color = 91;
	int index = 0;

	float x = 0;

	Key(Boolean Black, int index,ParticleSystem ps) {
		this.ps = ps;
		this.Black = Black;
		this.index = index;
	}
}
