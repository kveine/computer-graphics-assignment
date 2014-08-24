package util;

import java.awt.Container;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.image.TextureLoader;

public class TextureDistributor {

	public static Appearance LoadAppearance(String tex)
	{
		Appearance temp = new Appearance();
		TextureLoader loader = new TextureLoader(tex, "RGBA", new Container());
		Texture texture = loader.getTexture();
		TextureAttributes texAttr = new TextureAttributes();
		texAttr.setTextureMode(TextureAttributes.MODULATE);
		temp.setTexture(texture);
		temp.setTextureAttributes(texAttr);
		
		Material mat = new Material();
		mat.setAmbientColor(new Color3f(0.5f,0.5f,0.5f));
		mat.setDiffuseColor(new Color3f(0.7f,0.7f,0.7f));
		mat.setSpecularColor(new Color3f(0.3f,0.3f,0.3f));
		temp.setMaterial(mat);
		
		return temp;
	}
}
