CLASSPATH=.
CLASSPATH=$CLASSPATH:./dist/*
CLASSPATH=$CLASSPATH:./lib/*
export CLASSPATH
MAIN_CLASS=noti
java -server -Xmx256m -XX:+AggressiveOpts -XX:CompileThreshold=200 -cp $CLASSPATH $MAIN_CLASS 
