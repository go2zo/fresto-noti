CLASSPATH=.
CLASSPATH=$CLASSPATH:./dist/*
CLASSPATH=$CLASSPATH:./lib/*
export CLASSPATH
MAIN_CLASS=noti
nohup java -server -Xmx256m -XX:+AggressiveOpts -XX:CompileThreshold=200 -cp $CLASSPATH $MAIN_CLASS < /dev/null > event_noti.log 2>&1 &
