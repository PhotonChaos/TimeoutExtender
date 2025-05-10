# TimeoutExtender
This mod was build for a server I play on, some players were having trouble loading in and were getting timed out. This ports one change from [Corosauce's Timeout Fixes](https://github.com/Corosauce/TimeoutFixes) to 1.21.1. Was also an opportunity for me to dive into mixins, seems like they're pretty useful!
## How to Use
In the config file, you will find two variables. Only one does anything, `loginTimeoutExtraSeconds`. Setting this value to a number above 0 will make the game wait that many more seconds when a player is logging in before timing them out.
