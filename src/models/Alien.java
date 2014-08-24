package models;

import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import util.TextureDistributor;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

public class Alien extends TransformGroup implements Runnable {

	TransformGroup upperRightLegTG;
	TransformGroup upperLeftLegTG;
	TransformGroup upperRightArmTG;
	TransformGroup upperLeftArmTG;

	float posX;
	float posY;
	float posZ;
	
	int rotX;
	
	Transform3D bodyTransform = new Transform3D();
	Transform3D rightLegTransform = new Transform3D();
	Transform3D rightLegPosTransform = new Transform3D();
	Transform3D leftLegTransform = new Transform3D();
	Transform3D leftLegPosTransform = new Transform3D();
	float legMax = (float) Math.PI * 0.05f;
	float legRot = 0.0f;

	Transform3D rightArmTransform = new Transform3D();
	Transform3D rightArmPosTransform = new Transform3D();
	Transform3D leftlArmTransform = new Transform3D();
	Transform3D leftArmPosTransform = new Transform3D();
	float armMax = (float) Math.PI * 0.07f;
	float armRot = 0.0f;

	int direction = 1;
	float angle = 0f;
	
	//change shirt
	Cylinder body;
	List<Cylinder> upperArms= new ArrayList<Cylinder>();
	Cylinder upperArm;
	List<Sphere> elbows = new ArrayList<Sphere>();
	Sphere elbow;
	
	//change pants
	List<Cylinder> legs = new ArrayList<Cylinder>();
	List<Sphere> knees = new ArrayList<Sphere>();
	Cylinder upperLeg;
	Cylinder lowerLeg;
	Sphere knee;
	
	//change skin color
	List<Cylinder> skinCylinder = new ArrayList<Cylinder>();
	List<Sphere> skinSphere = new ArrayList<Sphere>();
	Cylinder lowerArm;
	Cylinder finger;
	Cylinder ear;
	Cylinder neck;
	Sphere earTip;
	Sphere hand;
	Sphere head;
	
	//change eyes
	List<Sphere> eyes = new ArrayList<Sphere>();
	Sphere eye;
	int primflags;

	// makes an alien with many limbs, where each limb has it own TransformGroup
	// and BranchGroup(if needed)

	public Alien() {
		// adds one directional light on alien
		setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		posX = 0.5f;
		posY = -0.3f;
		posZ = -3f;
		
		rotX = 1;
		Transform3D bodyTransform = new Transform3D();
		Vector3f bodyVector = new Vector3f(posX, posY, posZ);

		bodyTransform.setTranslation(bodyVector);
		setTransform(bodyTransform);

		// adds texture to alien body		
		Appearance shirt = TextureDistributor.LoadAppearance("tshirtTexture3.png");
		primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS + Primitive.ENABLE_APPEARANCE_MODIFY;

		body = new Cylinder(0.2f, 0.5f, primflags, shirt);
		BoundingSphere alienBounds = new BoundingSphere(new Point3d(0.0,0.0,0.0),1);
		body.setCollisionBounds(alienBounds);
		body.setCollidable(true);
		addChild(body);

		// setTransform(bodyTransform);
		BranchGroup bodyBG = new BranchGroup();
		addChild(bodyBG);

		// adds next limbs in the branch to the body BranchGroup.

		upperRightLegTG = new TransformGroup();
		upperRightLegTG.setCapability(ALLOW_TRANSFORM_WRITE);
		TransformGroup rLeg = upperLeg(-0.11f, -0.42f, 0f);
		upperRightLegTG.addChild(rLeg);

		upperLeftLegTG = new TransformGroup();
		upperLeftLegTG.setCapability(ALLOW_TRANSFORM_WRITE);
		TransformGroup lLeg = upperLeg(0.11f, -0.42f, 0f);
		upperLeftLegTG.addChild(lLeg);

		upperRightArmTG = new TransformGroup();
		upperRightArmTG.setCapability(ALLOW_TRANSFORM_WRITE);
		TransformGroup rArm = upperArm(-0.26f, 0.1f, 0f);
		upperRightArmTG.addChild(rArm);

		upperLeftArmTG = new TransformGroup();
		upperLeftArmTG.setCapability(ALLOW_TRANSFORM_WRITE);
		TransformGroup lArm = upperArm(0.26f, 0.1f, 0f);
		upperLeftArmTG.addChild(lArm);

		bodyBG.addChild(neck());
		bodyBG.addChild(upperRightLegTG);
		bodyBG.addChild(upperLeftLegTG);
		bodyBG.addChild(upperRightArmTG);
		bodyBG.addChild(upperLeftArmTG);

	}
	
	public void shirt1(){
		Appearance shirt = TextureDistributor.LoadAppearance("tshirt6.jpg");
		body.setAppearance(shirt);
		for(Cylinder c : upperArms){
			c.setAppearance(shirt);
		}
		for (Sphere s : elbows){
			s.setAppearance(shirt);
		}
		
	}

	public void shirt2(){
		Appearance shirt = TextureDistributor.LoadAppearance("tshirt2.png");
		body.setAppearance(shirt);
		for(Cylinder c : upperArms){
			c.setAppearance(shirt);
		}
		for (Sphere s : elbows){
			s.setAppearance(shirt);
		}
	}

	public void shirt3(){
		Appearance shirt = TextureDistributor.LoadAppearance("tshirt3.jpg");
		body.setAppearance(shirt);
		for(Cylinder c : upperArms){
			c.setAppearance(shirt);
		}
		for (Sphere s : elbows){
			s.setAppearance(shirt);
		}
	}

	public void shirt4(){
		Appearance shirt = TextureDistributor.LoadAppearance("tshirt4.png");
		Appearance shirtArms = TextureDistributor.LoadAppearance("tshirtArmTexture.jpg");
		body.setAppearance(shirt);
		for(Cylinder c : upperArms){
			c.setAppearance(shirtArms);
		}
		for (Sphere s : elbows){
			s.setAppearance(shirtArms);
		}
	}
	
	public void shirt5(){
		Appearance shirt = TextureDistributor.LoadAppearance("tshirt5.png");
		Appearance shirtArms = TextureDistributor.LoadAppearance("tshirtArmTexture.jpg");
		body.setAppearance(shirt);
		for(Cylinder c : upperArms){
			c.setAppearance(shirtArms);
		}
		for (Sphere s : elbows){
			s.setAppearance(shirtArms);
		}
	}
	
	public void shirt6(){
		Appearance shirt = TextureDistributor.LoadAppearance("tshirtTexture3.png");
		Appearance shirtArms = TextureDistributor.LoadAppearance("tshirtTexture2.jpg");
		body.setAppearance(shirt);
		for(Cylinder c : upperArms){
			c.setAppearance(shirtArms);
		}
		for (Sphere s : elbows){
			s.setAppearance(shirtArms);
		}
	}
	
	public void pants1(){
		Appearance pants = TextureDistributor.LoadAppearance("pants6.jpg");
		for(Cylinder c : legs){
			c.setAppearance(pants);
		}
		for (Sphere s : knees){
			s.setAppearance(pants);
		}
	}

	public void pants2(){
		Appearance pants = TextureDistributor.LoadAppearance("pants2.png");
		for(Cylinder c : legs){
			c.setAppearance(pants);
		}
		for (Sphere s : knees){
			s.setAppearance(pants);
		}
	}

	public void pants3(){
		Appearance pants = TextureDistributor.LoadAppearance("tshirt3.jpg");
		for(Cylinder c : legs){
			c.setAppearance(pants);
		}
		for (Sphere s : knees){
			s.setAppearance(pants);
		}
	}

	public void pants4(){
		Appearance pants = TextureDistributor.LoadAppearance("pants4.png");
		for(Cylinder c : legs){
			c.setAppearance(pants);
		}
		for (Sphere s : knees){
			s.setAppearance(pants);
		}
	}
	
	public void pants5(){
		Appearance pants = TextureDistributor.LoadAppearance("pants5.jpg");
		for(Cylinder c : legs){
			c.setAppearance(pants);
		}
		for (Sphere s : knees){
			s.setAppearance(pants);
		}
	}
	
	public void pants6(){
		Appearance pants = TextureDistributor.LoadAppearance("suitTexture4.jpg");
		for(Cylinder c : legs){
			c.setAppearance(pants);
		}
		for (Sphere s : knees){
			s.setAppearance(pants);
		}
	}

	public void skin1(){
		Appearance face = TextureDistributor.LoadAppearance("face4.png");
		Appearance skin = TextureDistributor.LoadAppearance("skin4.png");
		head.setAppearance(face);
		for(Cylinder c : skinCylinder){
			c.setAppearance(skin);
		}
		for (Sphere s : skinSphere){
			s.setAppearance(skin);
		}
	}
	
	public void skin2(){
		Appearance face = TextureDistributor.LoadAppearance("face2.png");
		Appearance skin = TextureDistributor.LoadAppearance("skin2.png");
		head.setAppearance(face);
		for(Cylinder c : skinCylinder){
			c.setAppearance(skin);
		}
		for (Sphere s : skinSphere){
			s.setAppearance(skin);
		}
	}
	
	public void skin3(){
		Appearance face = TextureDistributor.LoadAppearance("face3.png");
		Appearance skin = TextureDistributor.LoadAppearance("skin3.png");
		head.setAppearance(face);
		for(Cylinder c : skinCylinder){
			c.setAppearance(skin);
		}
		for (Sphere s : skinSphere){
			s.setAppearance(skin);
		}
	}
	
	public void skin4(){
		Appearance face = TextureDistributor.LoadAppearance("headTexture6.jpg");
		Appearance skin = TextureDistributor.LoadAppearance("skinTexture5.jpg");
		head.setAppearance(face);
		for(Cylinder c : skinCylinder){
			c.setAppearance(skin);
		}
		for (Sphere s : skinSphere){
			s.setAppearance(skin);
		}
	}
	
	public void eye1(){
		Appearance eye = TextureDistributor.LoadAppearance("eye5.png");
		for(Sphere s : eyes){
			s.setAppearance(eye);
		}
	}
	
	public void eye2(){
		Appearance eye = TextureDistributor.LoadAppearance("eye11.png");
		for(Sphere s : eyes){
			s.setAppearance(eye);
		}
	}
	
	public void eye3(){
		Appearance eye = TextureDistributor.LoadAppearance("eye33.jpg");
		for(Sphere s : eyes){
			s.setAppearance(eye);
		}
	}
	
	public void eye4(){
		Appearance eye = TextureDistributor.LoadAppearance("eye4.jpg");
		for(Sphere s : eyes){
			s.setAppearance(eye);
		}
	}
	
	public void eye5(){
		Appearance eye = TextureDistributor.LoadAppearance("eyeTexture5.jpg");
		for(Sphere s : eyes){
			s.setAppearance(eye);
		}
	}
	
	public void walkAnimation(){
		// walking animation
				if (direction == 1) {
					legRot = (legMax * 0.15f);
					armRot = (armMax * 0.2f);
					angle += legRot;

					if (angle > legMax) {
						direction = -1;
					}
				} else if (direction == -1) {
					legRot = -(legMax * 0.15f);
					armRot = -(armMax * 0.2f);
					angle += legRot;

					if (angle < -legMax) {

						direction = 1;
					}
				}

				rightLegTransform.rotX(legRot);
				rightLegPosTransform.mul(rightLegTransform);
				upperRightLegTG.setTransform(rightLegPosTransform);

				leftLegTransform.rotX(-legRot);
				leftLegPosTransform.mul(leftLegTransform);
				upperLeftLegTG.setTransform(leftLegPosTransform);

				rightArmTransform.rotX(-armRot);
				rightArmPosTransform.mul(rightArmTransform);
				upperRightArmTG.setTransform(rightArmPosTransform);

				leftlArmTransform.rotX(armRot);
				leftArmPosTransform.mul(leftlArmTransform);
				upperLeftArmTG.setTransform(leftArmPosTransform);
	}
	
	public void moveDown() {
		Transform3D bodyTransform = new Transform3D();
		
		walkAnimation();
		
		if(posZ < 1){
			posZ += 0.03f;
			bodyTransform.setTranslation(new Vector3f(posX, posY, posZ));
			setTransform(bodyTransform);
		}
	}

	public void moveUp() {
		rotX = 3;
		Transform3D bodyTransform = new Transform3D();
		Transform3D rotationTransform = new Transform3D();
		rotationTransform.rotY(rotX);
		bodyTransform.mul(rotationTransform);
		
		walkAnimation();
		
		if(posZ > -5){
			posZ -= 0.03f;
			bodyTransform.setTranslation(new Vector3f(posX, posY, posZ));
			setTransform(bodyTransform);
		}
	}

	public void moveLeft() {
		rotX = -1;
		Transform3D bodyTransform = new Transform3D();
		Transform3D rotationTransform = new Transform3D();
		rotationTransform.rotY(rotX);
		bodyTransform.mul(rotationTransform);
		
		walkAnimation();
		
		if(posX > -1){
			posX -= 0.03f;
			bodyTransform.setTranslation(new Vector3f(posX, posY, posZ));
			setTransform(bodyTransform);
		}
	}

	public void moveRight() {
		rotX = 1;
		Transform3D bodyTransform = new Transform3D();
		Transform3D rotationTransform = new Transform3D();
		rotationTransform.rotY(rotX);
		bodyTransform.mul(rotationTransform);
		
		walkAnimation();
		
		if(posX < 1.5){
			posX += 0.03f;
			bodyTransform.setTranslation(new Vector3f(posX, posY, posZ));
			setTransform(bodyTransform);
		}
	}


	private TransformGroup neck() {
		TransformGroup neckTG = new TransformGroup();
		Transform3D neckTransform = new Transform3D();
		Vector3f neckVector = new Vector3f(0f, 0.25f, 0f);
		neckTransform.setTranslation(neckVector);
		neckTG.setTransform(neckTransform);
		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("skinTexture5.jpg");
		neck = new Cylinder(0.1f, 0.2f, primflags, ap);
		skinCylinder.add(neck);
		neckTG.addChild(neck);
		BranchGroup neckBG = new BranchGroup();
		// adds next limbs in branch to neck BranchGroup
		neckTG.addChild(neckBG);
		neckBG.addChild(head());

		return neckTG;
	}

	private TransformGroup head() {
		TransformGroup headTG = new TransformGroup();
		Transform3D headTransform = new Transform3D();
		// scales head to make it look more alien
		headTransform.setScale(new Vector3d(1.5, 0.8, 1));
		Vector3f headVector = new Vector3f(0f, 0.25f, 0f);
		headTransform.setTranslation(headVector);
		headTG.setTransform(headTransform);
		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("headTexture6.jpg");
		head = new Sphere(0.2f, primflags, 300, ap);
		headTG.addChild(head);
		BranchGroup headBG = new BranchGroup();
		headTG.addChild(headBG);
		// adds next limbs in branch to head BranchGroup
		headBG.addChild(eye(-0.07f, 0.04f, 0.2f));
		headBG.addChild(eye(0.07f, 0.04f, 0.2f));
		headBG.addChild(ear(-0.22f, 0.1f, 0f, true));
		headBG.addChild(ear(0.22f, 0.1f, 0f, false));

		return headTG;
	}

	private TransformGroup eye(float x, float y, float z) {
		TransformGroup eyeTG = new TransformGroup();
		Transform3D eyeTransform = new Transform3D();
		eyeTransform.setScale(new Vector3d(1, 1, 0));
		Vector3f eyeVector = new Vector3f(x, y, z);
		eyeTransform.setTranslation(eyeVector);
		eyeTG.setTransform(eyeTransform);
		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("eyeTexture5.jpg");
		eye = new Sphere(0.05f, primflags, 300, ap);
		eyes.add(eye);
		eyeTG.addChild(eye);

		return eyeTG;
	}

	private TransformGroup ear(float x, float y, float z, boolean right) {
		TransformGroup earTG = new TransformGroup();
		Transform3D earTransform = new Transform3D();
		Vector3f earVector = new Vector3f(x, y, z);
		if (right) {
			Quat4f myQuat4f = new Quat4f(0, 0, 50, 90);
			earTransform.setRotation(myQuat4f);
		} else {
			Quat4f myQuat4f = new Quat4f(0, 0, -50, 90);
			earTransform.setRotation(myQuat4f);
		}

		earTransform.setTranslation(earVector);
		earTG.setTransform(earTransform);
		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("skinTexture5.jpg");
		ear = new Cylinder(0.03f, 0.13f, primflags, ap);
		skinCylinder.add(ear);
		earTG.addChild(ear);
		BranchGroup earBG = new BranchGroup();
		earTG.addChild(earBG);
		// adds next limbs in branch to ear BranchGroup
		earBG.addChild(earTip(0f, 0.1f, 0f));

		return earTG;
	}

	private TransformGroup earTip(float x, float y, float z) {
		TransformGroup earTipTG = new TransformGroup();
		Transform3D earTipTransform = new Transform3D();
		Vector3f earTipVector = new Vector3f(x, y, z);
		earTipTransform.setTranslation(earTipVector);
		earTipTG.setTransform(earTipTransform);
		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("skinTexture5.jpg");
		earTip = new Sphere(0.04f, primflags, 300, ap);
		skinSphere.add(earTip);
		earTipTG.addChild(earTip);

		return earTipTG;
	}

	private TransformGroup upperArm(float x, float y, float z) {
		TransformGroup upperArmTG = new TransformGroup();
		Transform3D upperArmTransform = new Transform3D();

		Vector3f upperArmVector = new Vector3f(x, y, z);
		upperArmTransform.setTranslation(upperArmVector);

		// Quat4f myQuat4f = new Quat4f(rx,ry,rz,90);
		// upperArmTransform.setRotation(myQuat4f);
		upperArmTG.setTransform(upperArmTransform);
		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("tshirtTexture2.jpg");
		/*primflags = Primitive.GENERATE_NORMALS
				+ Primitive.GENERATE_TEXTURE_COORDS;*/
		upperArm = new Cylinder(0.07f, 0.3f, primflags, ap);
		upperArms.add(upperArm);
		upperArmTG.addChild(upperArm);
		BranchGroup upperArmBG = new BranchGroup();
		upperArmTG.addChild(upperArmBG);
		// adds next limbs in branch to upperArm BranchGroup
		upperArmBG.addChild(elbow(0f, -0.18f, 0f));

		return upperArmTG;
	}

	private TransformGroup elbow(float x, float y, float z) {
		TransformGroup elbowTG = new TransformGroup();
		Transform3D elbowTransform = new Transform3D();
		Vector3f elbowVector = new Vector3f(x, y, z);
		elbowTransform.setTranslation(elbowVector);
		elbowTG.setTransform(elbowTransform);
		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("tshirtTexture2.jpg");
		/*int primflags = Primitive.GENERATE_NORMALS
				+ Primitive.GENERATE_TEXTURE_COORDS;*/
		elbow = new Sphere(0.07f, primflags, 300, ap);
		elbows.add(elbow);
		elbowTG.addChild(elbow);
		BranchGroup elbowBG = new BranchGroup();
		elbowTG.addChild(elbowBG);
		elbowBG.addChild(lowerArm(0f, -0.15f, 0f));

		return elbowTG;
	}

	private TransformGroup lowerArm(float x, float y, float z) {
		TransformGroup lowerArmTG = new TransformGroup();
		Transform3D lowerArmTransform = new Transform3D();
		Vector3f lowerArmVector = new Vector3f(x, y, z);
		lowerArmTransform.setTranslation(lowerArmVector);
		lowerArmTG.setTransform(lowerArmTransform);

		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("skinTexture5.jpg");
		lowerArm = new Cylinder(0.065f, 0.25f, primflags, ap);
		skinCylinder.add(lowerArm);
		lowerArmTG.addChild(lowerArm);
		BranchGroup lowerArmBG = new BranchGroup();
		lowerArmTG.addChild(lowerArmBG);
		// adds next limbs in branch to lowerArm BranchGroup
		lowerArmBG.addChild(hand(0f, -0.15f, 0f));

		return lowerArmTG;

	}

	private TransformGroup hand(float x, float y, float z) {
		TransformGroup handTG = new TransformGroup();
		Transform3D handTransform = new Transform3D();
		Vector3f handVector = new Vector3f(x, y, z);
		handTransform.setTranslation(handVector);
		handTG.setTransform(handTransform);
		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("skinTexture5.jpg");
		hand = new Sphere(0.07f, primflags, 300, ap);
		skinSphere.add(hand);
		handTG.addChild(hand);
		BranchGroup handBG = new BranchGroup();
		handTG.addChild(handBG);
		// adds next limbs in branch to hand BranchGroup
		handBG.addChild(finger(0f, -0.1f, 0.03f));
		handBG.addChild(finger(0f, -0.1f, -0.03f));

		return handTG;
	}

	private TransformGroup finger(float x, float y, float z) {
		TransformGroup fingerTG = new TransformGroup();
		Transform3D fingerTransform = new Transform3D();
		Vector3f fingerVector = new Vector3f(x, y, z);
		fingerTransform.setTranslation(fingerVector);
		fingerTG.setTransform(fingerTransform);
		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("skinTexture5.jpg");
		finger = new Cylinder(0.02f, 0.12f, primflags, ap);
		skinCylinder.add(finger);
		fingerTG.addChild(finger);

		return fingerTG;

	}

	private TransformGroup upperLeg(float x, float y, float z) {
		TransformGroup upperLegTG = new TransformGroup();
		Transform3D upperLegTransform = new Transform3D();
		Vector3f upperLegVector = new Vector3f(x, y, z);

		upperLegTransform.setTranslation(upperLegVector);
		upperLegTG.setTransform(upperLegTransform);
		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("suitTexture4.jpg");
		upperLeg = new Cylinder(0.09f, 0.3f, primflags, ap);
		legs.add(upperLeg);
		upperLegTG.addChild(upperLeg);
		BranchGroup upperLegBG = new BranchGroup();
		upperLegTG.addChild(upperLegBG);
		// adds next limbs in branch to upperLeg BranchGroup

		upperLegBG.addChild(knee(0f, -0.18f, 0f));

		return upperLegTG;
	}

	private TransformGroup knee(float x, float y, float z) {
		TransformGroup kneeTG = new TransformGroup();
		Transform3D kneeTransform = new Transform3D();
		Vector3f kneeVector = new Vector3f(x, y, z);
		kneeTransform.setTranslation(kneeVector);
		kneeTG.setTransform(kneeTransform);
		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("suitTexture4.jpg");
		knee = new Sphere(0.09f, primflags, 300, ap);
		knees.add(knee);
		kneeTG.addChild(knee);
		BranchGroup kneeBG = new BranchGroup();
		kneeTG.addChild(kneeBG);
		kneeBG.addChild(lowerLeg(0f, -0.1f, 0f));

		return kneeTG;
	}

	private TransformGroup lowerLeg(float x, float y, float z) {
		TransformGroup lowerLegTG = new TransformGroup();
		Transform3D lowerLegTransform = new Transform3D();
		Vector3f lowerLegVector = new Vector3f(x, y, z);
		lowerLegTransform.setTranslation(lowerLegVector);
		lowerLegTG.setTransform(lowerLegTransform);
		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("suitTexture4.jpg");
		lowerLeg = new Cylinder(0.08f, 0.3f, primflags, ap);
		legs.add(lowerLeg);
		lowerLegTG.addChild(lowerLeg);
		BranchGroup lowerLegBG = new BranchGroup();
		lowerLegTG.addChild(lowerLegBG);
		// adds next limbs in branch to lowerLeg BranchGroup
		lowerLegBG.addChild(foot(0f, -0.2f, 0.1f));

		return lowerLegTG;
	}

	private TransformGroup foot(float x, float y, float z) {
		TransformGroup footTG = new TransformGroup();
		Transform3D footTransform = new Transform3D();
		footTransform.setScale(new Vector3d(1, 1, 2.5));
		Vector3f footVector = new Vector3f(x, y, z);
		footTransform.setTranslation(footVector);
		footTG.setTransform(footTransform);
		// sets texture
		Appearance ap = TextureDistributor.LoadAppearance("shoeTexture.jpg");
		int primflags = Primitive.GENERATE_NORMALS
				+ Primitive.GENERATE_TEXTURE_COORDS;
		footTG.addChild(new Sphere(0.08f, primflags, 300, ap));

		return footTG;

	}

	@Override
	public void run() {
		// moveRight();

	}

}