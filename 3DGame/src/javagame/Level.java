package javagame;
import java.awt.AWTException;
import java.awt.Robot;
//import java.awt.Shape;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
//import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.*;

public class Level extends BasicGameState{
	Player p;
	double cz = .5;
	ArrayList<Box> b=new ArrayList<Box>();
	ArrayList<SpinningCube> cube=new ArrayList<SpinningCube>();
	ArrayList<Entity> Chip = new ArrayList<Entity>();
	double k=1;//-50;//Game.X/2;
	double l=1;//-50;//Game.Y/2;
	double disy=Game.Y/2;//1;//
	double disx=Game.X/2;//1;//
	public String mouse = "noinp";
	public Robot robot;
	static double rotx=0, roty=0, rotz=0, cosrx, sinrx, cosry, sinry, cosrz, sinrz; 
	double cosx, sinx,cosy,siny,cosz,sinz;
	Color color=Color.blue;
	boolean paused;
	int[][][] board;
	public Level(int state){
		p=new Player (4, 1, 1, 0, 0, 5);
	}
	public void reset(){
		blue = new Point(-1,-1,-1);
		int[][][] board = {{{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						},{ 
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 8, 0, 0, 0, 0, 0, 0, 0, 1},
						{1, 8, 0, 0, 0, 0, 0, 0, 0, 1},
						{1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
						{1, 1, 0, 1, 8, 8, 1, 0, 0, 0, 0, 0, 1},
						{1, 1, 0, 1, 8, 8, 1, 0, 0, 0, 5, 0, 1},
						{1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1},
						{1, 8, 0, 0, 0, 0, 0, 0, 1, 1},
						{1, 8, 0, 0, 0, 0, 0, 0, 0, 1},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						},{ 
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1,23,22, 1,24,24,24,24,24,24,24,24,24,24},
						{1, 1,24,21, 1,21, 1, 1, 1, 1, 1, 1, 1,24,24,24,24, 1},
						{1, 1, 1, 1, 1,21,22, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
						{1, 1, 1, 1,21, 1,21, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
						{1, 1, 1, 1,21, 1,21, 1, 1, 1, 1, 1, 1, 0, 0,22,22,22},
						{1, 1, 1, 1, 1, 1, 1, 1, 1,22,22,22,22,22,22,22,22},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
						},{ 
							{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
							{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
							{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							{1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
							{1, 0, 0, 0, 5, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0},
							{1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0},
							{1, 0, 0, 0, 0, 0, 0,21, 0, 1, 1, 1, 1, 0, 0, 0, 0},
							{1, 0, 0, 0, 0, 0,15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
							{1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
							{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
							},{ 
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
								{1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
								{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
								{1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 5, 0, 1},
								{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
								{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
								{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
								},{ 
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
									{1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
									{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
									{1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
									{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
									{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
									{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
									{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
									{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
									},{ 
										{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
										{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
										{1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
										{1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1},
										{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
										{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
										{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
										{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
										{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
										{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
										},{ 
											{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
											{1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
											{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
											{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
											{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
											{1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
											{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
											{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
											{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
											{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
											},{ 
												{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
												{1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
												{1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
												{1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
												{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
												{1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1},
												{1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1},
												{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
												{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
												{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
												},{ 
													{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
													{1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
													{1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
													{1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
													{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
													{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
													{1, 0, 0, 8, 0, 0, 0, 0, 0, 1, 1, 1, 1},
													{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
													{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
													{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
													},{ 
														{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
														{1, 0, 0, 0, 0, 0, 0, 1, 1, 1},
														{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
														{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
														{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
														{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
														{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
														{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
														{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
														{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
														},{ 
															{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
															{1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
															{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
															{1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1},
															{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
															{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
															{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
															{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
															{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
															{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
															},{ 
																{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																{1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
																{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
																{1, 0, 0, 0, 0, 5, 0, 0, 0, 1, 1, 1, 1},
																{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
																{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
																{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																},{ 
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	{1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
																	{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																	{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																	{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																	{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																	{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																	{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																	{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																	},{ 
																		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																		{1, 0, 0, 0, 0, 0, 0, 0, 5, 1},
																		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																		},{ 
																			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																			{1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
																			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																			},{ 
																				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																				{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																				{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																				{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																				{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																				{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																				{1, 0, 0, 0, 0, 0, 0, 5, 0, 1},
																				{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																				{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																				},{ 
																					{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																					{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																					{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																					{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																					{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																					{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																					{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																					{1, 0, 0, 0, 15, 0, 0, 0, 0, 1},
																					{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																					{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																					},{ 
																						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																						{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																						{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																						{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																						{1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
																						{1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
																						{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																						{1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
																						{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																						{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																						},{ 
																							{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																							{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																							{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																							{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																							{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																							{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																							{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																							{1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
																							{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																							{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																							},{ 
																								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																								{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																								{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																								{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																								{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																								{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																								{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																								{1, 0, 0, 0, 0, 0, 5, 0, 0, 1},
																								{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
																								{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
																								}};
		this.board=board;
		p=new Player (4, 1, 3, 90, 0, 5);
		Chip.clear();
		for(int z =0; z<board.length; z++){
			for(int x =0; x<board[z].length; x++){
				for(int y =0 ; y<board[z][x].length; y++){
					if(board[z][x][y]==8){
						Chip.add(new Chip(x, y, z));
						this.board[z][x][y]=0;
					}
					if(board[z][x][y]==5){
						Chip.add(new Launcher(x, y, z));
						this.board[z][x][y]=0;
					}
					if(board[z][x][y]==21){
						Chip.add(new Booster(x, y, z + 1, 0, .0015));
						this.board[z][x][y]=1;
					}if(board[z][x][y]==22){
						Chip.add(new Booster(x, y, z + 1, 1, .0015));
						this.board[z][x][y]=1;
					}if(board[z][x][y]==23){
						Chip.add(new Booster(x, y, z + 1, 2, .0015));
						this.board[z][x][y]=1;
					}if(board[z][x][y]==24){
						Chip.add(new Booster(x, y, z + 1, 3, .0015));
						this.board[z][x][y]=1;
					}if(board[z][x][y]==30){
						Chip.add(new Sphere(x, y, z));
						this.board[z][x][y]=0;
					}
					
					
					if(board[z][x][y]==15){
						Chip.add(new Rotator(x, y, z));
						this.board[z][x][y]=0;
					}
				}
			}
		}
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		Entity.board= board;
		try {
			robot=new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		reset();
		
		//b.add(new Box(1000, 10000, -1000000, 8, -10000, 100000));
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		
		if(p.dir.dy>90){
			p.dir.dy=90;
		}else if(p.dir.dy<-90){
			p.dir.dy=-90;
		}
		p.dir.updateTrig();
		cosrx = Math.cos(Math.toRadians(rotx));
		sinrx = Math.sin(Math.toRadians(rotx));
		cosry = Math.cos(Math.toRadians(roty));
		sinry = Math.sin(Math.toRadians(roty));
		cosrz = Math.cos(Math.toRadians(rotz));
		sinrz = Math.sin(Math.toRadians(rotz));
		g.setColor(Color.white);
		drawlevel(g);
		if(p.pos.x<1 && p.pos.x>-1 && p.pos.y<1 && p.pos.y>-1){
			
		}else{
			
		}
		for (int c =0; c<b.size(); c++){
			//b.get(c).draw(p.dir, p.pos, g, k, l, disx, disy, cosx, sinx, cosy, siny);
		}
		g.setColor(Color.green);
		for (int c =0; c<cube.size(); c++){
			//cube.get(c).draw(p.dir, p.pos, g, k, l, disx, disy, cosx, sinx, cosy, siny);
		}
		for (int c =0; c<Chip.size(); c++){
			//Chip.get(c).draw(p.dir, p.pos, g, k, l, disx, disy, cosx, sinx, cosy, siny);
		}
		g.setColor(Color.blue);
		g.drawString(sinx + " " + cosx, 20, 40);
		g.drawString(mouse + "  " + p.dir.dx + "  " + p.pos.x + "  " + p.pos.y + "  " +  p.pos.z, 20, 20);
		g.drawLine(Game.X/2+5, Game.Y/2, Game.X/2 -5, Game.Y/2);
		g.drawLine(Game.X/2, Game.Y/2 + 5, Game.X/2, Game.Y/2 -5);
		
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		cosx = Math.cos(Math.toRadians(p.dir.dx )); 
		sinx = Math.sin(Math.toRadians(p.dir.dx ));
		if(p.dir.dy>90){
			p.dir.dy=90;
		}else if(p.dir.dy<-90){
			p.dir.dy=-90;
		}
		double coscx = Math.cos(Math.toRadians(delta/20.0));
		double sincx = Math.sin(Math.toRadians(delta/20.0));
		
		for (int c =0; c<cube.size(); c++){
			cube.get(c).update(coscx, sincx);
		}
		p.dir.dx%=360;
		Input input= gc.getInput();
		if(input.isKeyDown(Input.KEY_W)){
			p.testmove(cosx, sinx, delta, board);//pos.x+=delta * .0005;
		}if(input.isKeyDown(Input.KEY_S)){
			p.testmove(-cosx, -sinx, delta, board);
		}if(input.isKeyDown(Input.KEY_D)){
			p.testmove( sinx,-cosx, delta, board);//pos.y-=delta * .0005;
		}if(input.isKeyDown(Input.KEY_A)){
			p.testmove(-sinx, cosx,  delta, board);//pos.y+=delta * .0005;
		} if(input.isKeyDown(Input.KEY_SPACE) && p.grounded){
			p.vz+=.005;
			p.grounded=false;
			//p.pos.z+=
		} if(input.isKeyDown(Input.KEY_LSHIFT)){
			//p.pos.z-=delta * .0005;
		}  if(input.isKeyDown(Input.KEY_U)){
			p.dir.dx+=delta * .05;//90;//
		}   if(input.isKeyDown(Input.KEY_O)){
			p.dir.dx-=delta * .05;//90;//
		} if (input.isKeyPressed(Input.KEY_R)){
			reset();//cube.add(new SpinningCube(p.pos.x, p.pos.y, p.pos.z));
		} //if (input.isMouseButtonDown(0)){rotx+=delta * .05;//Chip.add(new Bullet(p, .002, true));//input.isKeyDown(Input.KEY_L)){//rotx+=delta * .05;//90;
		 if (input.isMousePressed(0)){Chip.add(new Bullet(p, .08, false));//input.isKeyDown(Input.KEY_L)){//
		 		
		} if (input.isMousePressed(1)){//input.isKeyDown(Input.KEY_L)){
			Chip.add(new Bullet(p, true));
			//if (input.isMouseButtonDown(1)){rotx-=delta * .05;//90;
		} if (Mouse.isButtonDown(2)){//input.isKeyDown(Input.KEY_L)){
			rotx=0;//90;
			roty=0;
		} if (input.isKeyPressed(Input.KEY_J)){
			roty-=90;
		} if (input.isKeyDown(Input.KEY_K)){
			roty+=delta * .05;
		} if (input.isKeyDown(Input.KEY_I)){
			roty-=delta * .05;
		}if (input.isKeyPressed(Input.KEY_Z)){
		
			cube.clear();
		}if (input.isKeyPressed(Input.KEY_N)){
		
			sbg.enterState(2);
		}if (input.isKeyPressed(Input.KEY_P)){
			if(paused){
				paused=false;
			}else{
				paused=true;
			}
		}
		p.update(board, delta);
		for (int c =0; c<Chip.size(); c++){
			Chip.get(c).update(coscx, sincx, p, delta);
			if(Chip.get(c).collected){
				Chip.remove(c);
				c--;
			}
			
		}
		if(!paused){
		mouse=Mouse.getX()+" , "+ (Game.Y-Mouse.getY());
		RecenterMouse(gc);
		}
	    
		
	}
boolean centered;
int xcenter, ycenter;
	private void RecenterMouse(GameContainer gc) {
		
		p.dir.dx-=(Mouse.getX()-xcenter)*0.08;
		p.dir.dy+=(Game.Y-Mouse.getY()-ycenter	)*0.08;
		//System.out.println(Display.getX());
		if(robot != null){
			robot.mouseMove(Game.X/2, Game.Y/2);
		}
		if(!centered){
			setCenter(gc);
			centered = true;
		}
		
	}
	private void setCenter(GameContainer gc){
		if(robot != null){
			
		}robot.mouseMove(Game.X/2, Game.Y/2);
		xcenter = Mouse.getX();
		ycenter = Game.Y - Mouse.getY();
	}
	
	
	
	public int getID(){
		return 1;
	}
	
	public void drawlevel(Graphics g){
		loopz(false, false, false, g);
		loopz(false, false, true, g);
		loopz(false, true, false, g);
		loopz(false, true, true, g);
		loopz(true, false, false, g);
		loopz(true, false, true, g);
		loopz(true, true, false, g);
		loopz(true, true, true, g);
		
	}
	
	public void loopz(boolean px, boolean py, boolean pz, Graphics g){
		if (!pz){
			for(int z = 0; z<(int) (p.pos.z + .5) && z<board.length; z++){
				loopx(px, py, pz, z, g);
			}
		}else{
			for(int z = board.length -1; z > (int) (p.pos.z+ .5)-1 && z >-1; z--){
				loopx(px, py, pz, z, g);
			}
		}
		
	}
	public void loopx(boolean px, boolean py, boolean pz, int z, Graphics g){
		if (!px){
			for(int x =0; x<(int) (p.pos.x+ .5); x++){
				loopy(px, py, pz, z, x,  g);
			}
			
		}else{
			for(int x =board[z].length-1; x>(int) (p.pos.x+ .5)-1; x--){
				loopy(px, py, pz, z, x,  g);
			}
		}
	}
	public void loopy(boolean px, boolean py, boolean pz, int z, int x, Graphics g){
		if (!py){
			for(int y =0 ; y<(int) (p.pos.y+ .5); y++){
				try{
					drawblock(x, y, z, px, py, pz, g);
				}catch(Exception e){}
			}
		}else{
			for(int y =board[z][x].length -1  ; y>(int) (p.pos.y+ .5)-1; y--){
				try{
					drawblock(x, y, z, px, py, pz, g);
				}catch(Exception e){}
			}
		}
	}
	
	double cs=.45;
	Color BlockColor;
	public void drawblock(int x, int y, int z, boolean px, boolean py, boolean pz, Graphics g){
		g.setColor(Color.green);
		if (board[z][x][y] == 1){
			if(x==blue.x && y == blue.y && z == blue.z){
				BlockColor = Color.red;
			}else{
				BlockColor = Color.green;
			}
			drawtile(x, y, z, px, py, pz, g);
		}
		g.setColor(Color.blue);
		if (board[z][x][y] == 10){
			drawtile(x, y, z, px, py, pz, g);
		}
		for (int c= 0; c<Chip.size(); c++){
			if(Chip.get(c).x > x - .5 && Chip.get(c).y > y - .5 && Chip.get(c).z > z - .5 && Chip.get(c).x < x + .5 && Chip.get(c).y < y + .5 && Chip.get(c).z < z + .5){
				Chip.get(c).draw(p.dir, p.pos, g, k, l, disx,  disy);
				if(Chip.get(c)  instanceof Bullet  && board[z][x][y] ==1){
					if(Chip.get(c).add){
						Point p = Chip.get(c).getSide().add(x, y, z);
						if (p != null){// && board[(int)p.z][(int)p.x][(int)p.y]== 0){
							board[(int)p.z][(int)p.x][(int)p.y] = 1;
							System.out.println(x + " " + y + " " + z + " " + p.x + " " + p.y + " " + p.z);
							Chip.remove(c);
						}
					}else{
						if(x==blue.x && y == blue.y && z == blue.z){
							board[z][x][y] = 0;
						}blue = new Point(x, y, z);
					
						Chip.remove(c);
					}
				}
			}
		}
	}
	Point blue;
	public void drawtile (double x, double y, double z, boolean px, boolean py, boolean pz, Graphics g){
		if(!pz){// && (int) (p.pos.z -.5) != z){
		try{
		if (board[(int) z + 1][(int) x][(int) y] != 1 ){
			drawfloor(x, y, z + .5, g);
		}}catch(Exception e){drawfloor(x, y, z + .5, g);}
		}else{
		try{
		if (board[(int) z - 1][(int) x][(int) y] != 1 ){
			drawfloor(x, y, z -.5, g);
		}}catch(Exception e){}
		}
		if((int) (p.pos.x +.5) != x){
			if(!px){
				try{
					if (board[(int) z][(int) x +1][(int) y] != 1 ){
						drawwallns(x +.5, y, z, g);
					}}catch(Exception e){}
			}else{
				try{
					if (board[(int) z][(int) x -1][(int) y] != 1 ){
						drawwallns(x-.5, y, z, g);
					}}catch(Exception e){}
			}
		}	
		if((int) (p.pos.y+.5) != y){
			if(!py){
				try{
					if (board[(int) z][(int) x][(int) y + 1] != 1 ){
						drawwallew(x, y+.5, z, g);
					}}catch(Exception e){}
				}else{
					try{
						if (board[(int) z][(int) x][(int) y - 1] != 1 ){
							drawwallew(x, y-.5, z, g);
				}}catch(Exception e){}
			}	
		}
		
	}
	
	public void drawfloor (double x, double y, double z, Graphics g){
		Point[] p=new Point[4];
		cs=.5;
		color=Color.black;
		p[0]=new Point(x -cs, y -cs, z);
		p[1]=new Point(x -cs, y +cs, z);
		p[2]=new Point(x +cs, y +cs, z);
		p[3]=new Point(x +cs, y -cs, z);
		drawsquare (p, g);
		cs=.45;
		color=BlockColor;
		p[0]=new Point(x -cs, y -cs, z);
		p[1]=new Point(x -cs, y +cs, z);
		p[2]=new Point(x +cs, y +cs, z);
		p[3]=new Point(x +cs, y -cs, z);
		drawsquare (p, g);
	}
	public void drawwallns (double x, double y, double z, Graphics g){
		Point[] p=new Point[4];
		cs=.5;
		color=Color.black;
		p[0]=new Point(x, y -cs, z -cs);
		p[1]=new Point(x, y +cs, z -cs);
		p[2]=new Point(x, y +cs, z +cs);
		p[3]=new Point(x, y -cs, z +cs);
		drawsquare (p, g);
		cs=.45;
		color=BlockColor;
		p[0]=new Point(x, y -cs, z -cs);
		p[1]=new Point(x, y +cs, z -cs);
		p[2]=new Point(x, y +cs, z +cs);
		p[3]=new Point(x, y -cs, z +cs);
		drawsquare (p, g);
	}
	public void drawwallew (double x, double y, double z, Graphics g){
		Point[] p=new Point[4];
		cs=.5;
		color=Color.black;
		p[0]=new Point(x-cs, y , z -cs);
		p[1]=new Point(x+cs, y , z -cs);	
		p[2]=new Point(x+cs, y , z +cs);
		p[3]=new Point(x-cs, y , z +cs);
		drawsquare (p, g);
		cs=.45;
		color=BlockColor;
		p[0]=new Point(x-cs, y , z -cs);
		p[1]=new Point(x+cs, y , z -cs);
		p[2]=new Point(x+cs, y , z +cs);
		p[3]=new Point(x-cs, y , z +cs);
		drawsquare (p, g);
	}
	
	public void drawsquare (Point[] p, Graphics g){
		Vector[] v = new Vector[4];
		int t =5;
		Polygon b = new Polygon();
		for(int c=0; c<4; c++){
			v[c]=this.p.dir.rotateGlobal(this.p.pos, p[c], cosrx, sinrx, cosry, sinry, cosrz, sinrz);
		
			if(v[c].dx < t &&  v[c].dx > -t  && v[c].dy < t && v[c].dy>-t){
				b.addPoint((float) ((v[c].dx + k)*disx), (float) ((v[c].dy + l)*disy));
			}else{
				Vector s = this.p.dir.rotateGlobal(this.p.pos, new Point((p[c].x + p[(c+5)%4].x)/2,(p[c].y + p[(c+5)%4].y)/2, (p[c].z + p[(c+5)%4].z)/2 ), cosrx, sinrx, cosry, sinry, cosrz, sinrz);
				if(s.dx < t &&  s.dx > -t  && s.dy < t && s.dy>-t)
					b.addPoint((float) ((s.dx + k)*disx), (float) ((s.dy + l)*disy));
				s = this.p.dir.rotateGlobal(this.p.pos, new Point((p[c].x + p[(c+3)%4].x)/2,(p[c].y + p[(c+3)%4].y)/2, (p[c].z + p[(c+3)%4].z)/2 ), cosrx, sinrx, cosry, sinry, cosrz, sinrz);
				if(s.dx < t &&  s.dx > -t  && s.dy < t && s.dy>-t)
					b.addPoint((float) ((s.dx + k)*disx), (float) ((s.dy + l)*disy));
			}
		
		
		}
		g.setColor(Color.black);
		g.fill(b);
		g.setColor(color);
		
		for(int c=0; c<4; c++){
			if(v[c].dx < t &&  v[c].dx > -t  && v[c].dy < t && v[c].dy>-t && v[(c+1)%4].dx < t &&  v[(c+1)%4].dx > -t  && v[(c+1)%4].dy < t && v[(c+1)%4].dy>-t)
			g.drawLine((float)(( v[c].dx + k)*disx), (float)(( v[c].dy + l)* disy), (float)(( v[(c+1)%4].dx + k)* disx),(float)(( v[(c+1)%4].dy + l)*disy));
		}
		
		
		
	}
}
