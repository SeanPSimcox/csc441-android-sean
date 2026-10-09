1) less than a minute.
2) the background changed to black.
3) everything that we've gone over makes sense.

Week 5, Friday.
1) Swapping .fillMaxWidth for .fillMaxSize made the image take over the whole screen.

Week 6, Wednesday.
1) ---------------------------- PROCESS STARTED (15360) for package edu.lemoyne.campusapp ----------------------------
   2026-10-02 15:51:16.884 15360-15360 System.out              edu.lemoyne.campusapp                I  count is now 1
   2026-10-02 15:51:17.203 15360-15360 System.out              edu.lemoyne.campusapp                I  count is now 2
   2026-10-02 15:51:17.508 15360-15360 System.out              edu.lemoyne.campusapp                I  count is now 3
   2026-10-02 15:51:18.145 15360-15360 System.out              edu.lemoyne.campusapp                I  count is now 4
   2026-10-02 15:51:18.568 15360-15360 System.out              edu.lemoyne.campusapp                I  count is now 5

2) We didn't handle the state for count, so when the button changed the variable, Compose didn't
know the screen needed to update
3) Remember stores the status of variable so when things reload there values aren't lost. The values
would be lost otherwise.

Week 6, Friday.
1) I added a rule requiring at least one letter because a network port entry should identify a service
and not only a numeric port value. Entries such as "443" or "3389" alone are less descriptive than 
including the service name.
2) The conditions in a when statement are evaluated from top to bottom. The first condition that
evaluates to true runs, so the order of the conditions matters.

Week 7, Wednesday.
1) I rotated the homescreen, and was on the homescreen.
2) No, I lost anything that didn't fit on the screen.
3) currentScreen survives rotation because it uses rememberSaveable, while added ports are lost
because the ports list only uses remember.