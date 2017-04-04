package com.mike.ThreadLearning.Traditional.ReadWriter;

public interface Data {

	/*synchronized*/ String read();

	/*synchronized*/ void write(char c);

}