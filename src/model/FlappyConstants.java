package model;

public class FlappyConstants {
	// flappy size in pixels or whatever
	public static final int FLAPPY_WIDTH = 32;
	// width of a pipe is 2 flappys
	public static final int PIPE_WIDTH = 2 * FLAPPY_WIDTH;
	// space between pipes ( from pipe a to b)
	public static final int PIPE_GAP = 8 * FLAPPY_WIDTH;
	
	// these are obvious
	public static final int SCREEN_WIDTH = 14 * FLAPPY_WIDTH;
	public static final int SCREEN_HEIGHT = 16 * FLAPPY_WIDTH;
	
	// acceleration due to gravity
	public static final double GRAVITY = 0.9;
	// max fall speed
	public static final int TERMINAL_VELOCITY = 5;
	
	// can be up to 6 flappy's long
	public static final int MAX_TOP_PIPE_LENGTH = 7;
	// must be at least 2 flappy's
	public static final int MIN_TOP_PIPE_LENGTH = 1;
	// 6 flappy's fit inbetween top and bottom (I think its normally 4)
	public static final int PIPE_SPACING = 6 * FLAPPY_WIDTH;
	// start at 5 flappies in
	public static final int FLAPPY_X = 5 * FLAPPY_WIDTH;
	// 8 down
	public static final int FLAPPY_Y = 8 * FLAPPY_WIDTH;
	// ground starts 14 flappy's down
	public static final int GROUND_Y = 14 * FLAPPY_WIDTH;
	// Just how big the ground should be
	public static final int GROUND_HEIGHT = 2 * FLAPPY_WIDTH;
	
	// Pipes move 2 pixels or whatever
	public static final int PIPE_MOVEMENT = 2;
	
	// Increase heigth by 15 when jumping
	public static final int JUMP_HEIGHT = 15;
	
	
}
