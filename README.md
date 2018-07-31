# DreamOS_Core
현재 개발중인 DreamOS의 논그래픽 엔진입니다.
베타버전은 컴파일링 되어있지 않은 .java 상태로 업로드됩니다.
안정버전은 .jar 파일로 컴파일되어 업로드됩니다.

개발 로그:
Engine Run Test 1 (build 0)
- Initial commit

Engine Run Test 2 (build 4)
- Updated boot engine
- Enhanced LibMGR stability

Test Beta 3 (build 18B072742UD-TB3)
- Added command: list
- Added command: fs
- Added command: delete
- Added command: fasthelp
- Added command: help
- Added command: write
- Added command: read
- Added setting modification feature at write command
- Added setting read feature at read command
- Added input indicator: >
- Added unknown command error output
- Repair errors

Test Beta 4 (build18B072826UD-TB4)
- Added command: reboot
- Added command: cd
- Added command: mkdir
- Added command: delete -d
- Updated File reader (Failure when user tried to read directory)
- Added DeleteFolder
- Added MakeDir

Test Beta 5 (build 18B072847UD-TB5)
- Patched custom boot hash causing E:47

Test Beta 6 (build 18B072967UD-TB6)
- Patched root exploit when lock option is enabled
- Patched signature writing variable link
- Patched ReadFile class error
- Patched skipping login when secure option is disabled
- Patched displaying broken root lock option message as a broken signature check option.
- Patched automatic login error

Test Beta 7 (build 18B073127UD-TB7)
- Patched deletable system directory (Permission patch)
- Patched security exploit
- Default boot hash is now the same as signature hash (Not null)
- Started creating migration helper
- Started creating safeOS
- Started creating recoveryOS