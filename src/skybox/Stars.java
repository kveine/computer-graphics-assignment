package skybox;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import util.TextureDistributor;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;

public class Stars extends TransformGroup{
	//adds a box in the back with star texture
	public Stars(){
		Transform3D starsTransform = new Transform3D();
		Vector3f starsVector = new Vector3f(0f, 0f, -20f);
		starsTransform.setTranslation(starsVector);
		setTransform(starsTransform);
		//adds texture
		Appearance ap = TextureDistributor.LoadAppearance("spaceTexture3.jpg");
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		Box star = new Box(10f, 10f, 0.1f, primflags, ap);
		addChild(star);
		BoundingSphere starBounds = new BoundingSphere(new Point3d(0.0,0.0,0.0),1);
		star.setCollisionBounds(starBounds);
		star.setCollidable(true);
		BranchGroup starBG = new BranchGroup();
		addChild(starBG);
		
	}
}