package models;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {
	
	Alien alien;
	
	public MyKeyListener(Alien alien){
		this.alien = alien;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
			switch (e.getKeyCode()){
			//move
        	case KeyEvent.VK_LEFT:
        		alien.moveLeft();
        		break;
        	case KeyEvent.VK_RIGHT:
        		alien.moveRight();
    			break;
        	case KeyEvent.VK_UP:
        		alien.moveUp();
        		break;
        	case KeyEvent.VK_DOWN:
        		alien.moveDown();
        		break;
		
        	//change tshirt	
        	case KeyEvent.VK_Q:
        		alien.shirt1();
        		break;
        	case KeyEvent.VK_W:
        		alien.shirt2();
        		break;
        	case KeyEvent.VK_E:
        		alien.shirt3();
        		break;
        	case KeyEvent.VK_R:
        		alien.shirt4();
        		break;	
        	case KeyEvent.VK_T:
        		alien.shirt5();
        		break;
        	case KeyEvent.VK_Y:
        		alien.shirt6();
        		break;
        		
        	//change pants	
        	case KeyEvent.VK_A:
        		alien.pants1();
        		break;
        	case KeyEvent.VK_S:
        		alien.pants2();
        		break;
        	case KeyEvent.VK_D:
        		alien.pants3();
        		break;
        	case KeyEvent.VK_F:
        		alien.pants4();
        		break;
        	case KeyEvent.VK_G:
        		alien.pants5();
        		break;
        	case KeyEvent.VK_H:
        		alien.pants6();
        		break;
        		
        	//change skin color
        	case KeyEvent.VK_1:
        		alien.skin1();
        		break;
        	case KeyEvent.VK_2:
        		alien.skin2();
        		break;
        	case KeyEvent.VK_3:
        		alien.skin3();
        		break;
        	case KeyEvent.VK_4:
        		alien.skin4();
        		break;
        		
        	//change eye
        	case KeyEvent.VK_5:
        		alien.eye1();
        		break;
        	case KeyEvent.VK_6:
        		alien.eye2();
        		break;
        	case KeyEvent.VK_7:
        		alien.eye3();
        		break;
        	case KeyEvent.VK_8:
        		alien.eye4();
        		break;
        	case KeyEvent.VK_9:
        		alien.eye5();
        		break;
		}
		
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
