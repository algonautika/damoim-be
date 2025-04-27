## 🛠️ AWS ECS 기반 자동 배포 아키텍처
<img width="784" alt="스크린샷 2025-04-27 오후 8 30 00" src="https://github.com/user-attachments/assets/26231e99-be10-4562-9a23-011794b2843e" />

본 프로젝트는 GitHub Actions, AWS ECR, AWS ECS, AWS ELB를 활용하여 자동화된 CI/CD 파이프라인과 보안 강화형 네트워크 구조를 갖춘 서비스를 구축합니다.
- 코드를 GitHub에 푸시하면 자동으로 Docker 이미지가 빌드 및 배포됩니다.
- 외부 사용자 요청은 AWS ELB를 통해 내부 Private Subnet의 ECS 서비스로 안전하게 전달됩니다.
- 모든 ECS 서비스는 Private Subnet에서 운영되어 외부 노출을 최소화합니다.

