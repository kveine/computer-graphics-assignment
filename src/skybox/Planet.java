package skybox;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import util.TextureDistributor;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

public class Planet extends TransformGroup{
	//makes a planet for the models to stand on
	public Planet(){
		addChild(alienLight());
		Transform3D planetTransform = new Transform3D();
		//scales the sphere to make bigger on z-axis and x-axis
		planetTransform.setScale(new Vector3d(8 ,0.5, 20));
		Vector3f planetVector = new Vector3f(0.2f, -2f, 0f);
		planetTransform.setTranslation(planetVector);
		setTransform(planetTransform);
		//adds texture 
		Appearance ap = TextureDistributor.LoadAppearance("moonTexture.jpg");
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		addChild(new Sphere(1.5f, primflags,300, ap));
	}
	
	// sets up directional light
		private DirectionalLight alienLight() {
			BoundingSphere bounds = new BoundingSphere(new Point3d(1, 1, 3), 1000000);
			Color3f alienLightColor = new Color3f(0.4f, 0.4f, 0.4f);
			Vector3f alienLightVector = new Vector3f(-1f, -1f, -1f);
			DirectionalLight alienLight = new DirectionalLight(alienLightColor,
					alienLightVector);
			alienLight.setInfluencingBounds(bounds);

			return alienLight;
		}
}
